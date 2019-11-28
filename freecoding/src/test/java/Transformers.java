import com.google.common.collect.ImmutableList;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class Transformers {
    @Test
    public void observable_transform_with_collect(){
        ImmutableList.Builder<String> builder = ImmutableList.<String>builder();
        ImmutableList<String> build = builder.add().build();

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .collect(ImmutableList::builder, ImmutableList.Builder::add)
                .map(ImmutableList.Builder::build)
                .subscribe(System.out::println);

        Observable.range(1,15)
                .collect(ImmutableList::builder, ImmutableList.Builder::add)
                .map(ImmutableList.Builder::build)
                .subscribe(System.out::println);
    }

    public static <T> ObservableTransformer<T, ImmutableList<T>> toObservableTransformer(){
        return upstream ->
                upstream.collect(ImmutableList::<T>builder, ImmutableList.Builder::add)
                        .map(ImmutableList.Builder::build)
                        .toObservable();
    }

    @Test
    public void compose_with_observable_transfomer(){
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .compose(toObservableTransformer())
                .subscribe(System.out::println);

        Observable.range(1,15)
                .compose(toObservableTransformer())
                .subscribe(System.out::println);
    }

    static ObservableTransformer<String, String> toJoinString(String separator){
        return upstream -> upstream
                .collect(StringBuilder::new, (b,s)-> {
                    if (b.length() == 0)
                        b.append(s);
                    else
                        b.append(separator).append(s);
                })
                .map(StringBuilder::toString)
                .toObservable();
    }

    @Test
    public void compose_with_JointoString() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .compose(toJoinString("/"))
                .subscribe(System.out::println);
    }

    public static <T> FlowableTransformer<T, ImmutableList<T>> toImmutableList(){
        return flowable->flowable
                .collect(ImmutableList::<T>builder, ImmutableList.Builder::add)
                .map(ImmutableList.Builder::build)
                .toFlowable();
    }

    @Test
    public void flowableTransformer(){
        Flowable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .compose(toImmutableList())
                .subscribe(System.out::println);

        Flowable.range(1,10)
                .compose(toImmutableList())
                .subscribe(System.out::println);
    }

    @ToString
    @AllArgsConstructor
    public static class IndexedValue<T>{
        private int index;
        private T value;
    }

    static <T> ObservableTransformer<T, IndexedValue<T>> toMyIndexTransformer(){
        AtomicInteger indexer = new AtomicInteger(-1);
        return upstream->upstream
                .map(s->new IndexedValue<T>(indexer.incrementAndGet(), s));
    }

    @Test
    public void error_transformer_with_resource(){
        Observable<String> observable = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<IndexedValue<String>> indexStrings = observable.compose(toMyIndexTransformer());

        indexStrings.subscribe(i -> System.out.println("Same transformer Received 1 : " + i));
        indexStrings.subscribe(i -> System.out.println("Same transformer Received 2 : " + i));

        observable.compose(toMyIndexTransformer()).subscribe(i -> System.out.println("Other transformer Received 1 : " + i));
        observable.compose(toMyIndexTransformer()).subscribe(i -> System.out.println("Other transformer Received 2 : " + i));
    }

    static <T> ObservableTransformer<T, IndexedValue<T>> resourceOutOfDefer(){
        AtomicInteger atomicInteger = new AtomicInteger(-1);
        return upstream->Observable.defer(()->{
            return upstream
                .map(s->new IndexedValue<T>(atomicInteger.incrementAndGet(), s));
        });
    }

    static <T> ObservableTransformer<T,IndexedValue<T>> resourceInDefer() {
        return upstream -> Observable.defer(() -> {
            AtomicInteger indexer = new AtomicInteger(-1);
            return upstream.map(v -> new IndexedValue<T>(indexer.incrementAndGet(), v));
        });
    }

    @Test
    public void fix_transformer_with_resource(){
        Observable<IndexedValue<String>> observable = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .compose(resourceOutOfDefer());

        observable.subscribe(indexedValue -> System.out.println("resourceOutOfDefer Received 1 : " + indexedValue));
        observable.subscribe(indexedValue -> System.out.println("resourceOutOfDefer Received 2 : " + indexedValue));

        System.out.println("==========");

        Observable<IndexedValue<String>> observable2 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .compose(resourceInDefer());

        observable2.subscribe(indexedValue -> System.out.println("resourceInDefer Received 1 : " + indexedValue));
        observable2.subscribe(indexedValue -> System.out.println("resourceInDefer Received 2 : " + indexedValue));
    }

    static <T> ObservableTransformer<T, IndexedValue<T>> withZip(){
        return upstream -> Observable.zip(upstream,
                Observable.range(0, Integer.MAX_VALUE),
                (ups, index) -> new IndexedValue<T>(index, ups));
    }

    @Test
    public void fix_transform_with_zip(){
        Observable<IndexedValue<String>> observable = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .compose(withZip());

        observable.subscribe(i -> System.out.println("withZip Received 1 : " + i));
        observable.subscribe(i -> System.out.println("withZip Received 2 : " + i));
    }
}
