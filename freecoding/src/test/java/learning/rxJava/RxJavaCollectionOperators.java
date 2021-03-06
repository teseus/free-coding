package learning.rxJava;

import com.google.common.collect.ImmutableList;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class RxJavaCollectionOperators {
    @Test
    public void ToListOperator(){
        Single<List<String>> listSingle = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toList();
        listSingle.subscribe(s-> System.out.println("Received 1 :" + s));

        Single<CopyOnWriteArrayList<String>> copyOnWriteArrayListSingle = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toList(CopyOnWriteArrayList::new);

        copyOnWriteArrayListSingle.subscribe(s-> System.out.println("Received 2 :" + s));

        Observable.range(1, 10)
                .toList()
                .subscribe(s-> System.out.println("Received 3 :" + s));


        Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
                .toSortedList()
                .subscribe(s-> System.out.println("Received 4 :" + s));
    }

    @Test
    public void ToMap_Operator(){
        Single<Map<Character, String>> mapSingle = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMap(s -> s.charAt(0));
        mapSingle.subscribe(s-> System.out.println("Received 1 :" + s));

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMap(s->s, v->v.length())
                .subscribe(s-> System.out.println("Received 2 :" + s));

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMap(s->s.charAt(0), v->v.length(), () -> new ConcurrentHashMap<>())
                .subscribe(s-> System.out.println("Received 3 :" + s));

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMap(s->s.length())
                .subscribe(s-> System.out.println("Received 4 :" + s));

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMultimap(s->s.length())
                .subscribe(s-> System.out.println("Received :" + s));
    }

    @Test
    public void Collect_Operator() {
        Observable.just("Alpha", "Beta", "Gamma", "Eta", "Epsilon")
                .collect(HashSet::new, HashSet::add)
                .subscribe(i -> System.out.println("HashSet Received :" + i), Throwable::printStackTrace);

        ImmutableList.builder();

        Observable.just("Alpha", "Beta", "Gamma", "Eta", "Epsilon")
                .collect(ImmutableList::builder, ImmutableList.Builder::add)
                .map(ImmutableList.Builder::build)
                .subscribe(i -> System.out.println("ImmutableList Received :" + i), Throwable::printStackTrace);
    }

    @Test
    public void ErrorRecovery_Operfator(){
        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .map(i->10/i)
                .subscribe(i-> System.out.println("Received :" + i), e-> System.out.println(e)
                        , () -> System.out.println("Done"));

        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .map(i->10/i)
                .onErrorReturnItem(-1)
                .subscribe(i-> System.out.println("onErrorReturnItem Received :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Done"));

        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .map(i->10/i)
                .onErrorReturn(e->-1)
                .subscribe(i-> System.out.println("onErrorReturn Received :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Done"));

        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .map(i->{
                    try {
                        return 10/i;
                    } catch (ArithmeticException e) {
                        return -1;
                    }
                })
                .subscribe(i-> System.out.println("Try Catch Received :" + i), Throwable::printStackTrace
                , () -> System.out.println("Take Done"));
    }

    @Test
    public void OnErrorResumeNext_Operator(){
        Observable.just(5,2,4,0,3,2,8)
                .map(i->10/i)
                .onErrorResumeNext(Observable.just(-1).repeat(3))
                .subscribe(i-> System.out.println("onErrorResumeNext Received 1 : " + i), Throwable::printStackTrace
                ,()-> System.out.println("Done"));

        Observable.just(5,2,4,0,3,2,8)
                .map(i->10/i)
                .onErrorResumeNext(Observable.empty())
                .subscribe(i-> System.out.println("onErrorResumeNext Received 2:" + i), Throwable::printStackTrace
                        , () -> System.out.println("Done"));

        Observable.just(5,2,4,0,3,2,8)
                .map(i->10/i)
                .onErrorResumeNext((Throwable e)->Observable.just(-1).repeat(3))
                .subscribe(i-> System.out.println("Try Catch Received :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Done"));
    }

    @Test
    public void Retry_Operator(){
        Observable.just(5,2,4,0,3,2,8)
                .map(i->10/i)
                .retry(2)
                .subscribe(i-> System.out.println("Try Catch Received :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Take Done"));
    }

    @Test
    public void DoOnNext_DoOnComplete_DoOnError_Operator(){
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .doOnNext(i-> System.out.println("Processing: " + i))
                .map(String::length)
                .subscribe(i-> System.out.println("DoOnNext Received :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Done"));


        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .doOnComplete(() -> System.out.println("Source Emmitting is Completed"))
                .map(String::length)
                .subscribe(i-> System.out.println("DoOnComplete Received :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Done"));

        Observable.just(5,2,4,0,3,2,8)
                .doOnError(e-> System.out.println("Source emintting eror"))
                .map(i->10/i)
                .doOnError(e-> System.out.println("Error on Mapping"))
                .subscribe(i-> System.out.println("DoOnError Received :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Done"));

    }

    @Test
    public void DoOnSubscribe_DoOnDispose_Operator() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .doOnSubscribe(s -> System.out.println("Subscribing :" + s))
                .doOnDispose(() -> System.out.println("Disposing"))
                .subscribe(i -> System.out.println("Received :" + i));
    }

    @Test
    public void DoOnSuccess_Operator() {
        Observable.just(5, 3, 7, 10, 2, 14)
                .reduce((x, y) -> x + y)
                .doOnSuccess(i -> System.out.println("Emmitting :" + i))
                .subscribe(i -> System.out.println("Received :" + i));
    }

}
