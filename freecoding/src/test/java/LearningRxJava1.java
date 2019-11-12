import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class LearningRxJava1 {
    private static final String SOME_KEY = "FOO";
    int count = 5;

    @Test
    public void 단순옵져버블_create() {
        Observable.create(e-> {
            e.onNext("a");
            e.onNext("b");
            e.onNext("c");
            e.onNext("d");
            e.onComplete();
        }).subscribe(s-> System.out.println("Received "+s));
    }

    @Test
    public void 단순옵져버블_just() {
        Observable.just("a", "b", "c", "d", "e")
                .subscribe(s-> System.out.println("Received " + s));
    }

    @Test
    public void 단순옵져버블_Exception_TryCatch(){
        Observable.create(emitter -> {
            try {
                emitter.onNext("Alpha");
                emitter.onNext("Beta");
                emitter.onNext("Gamma");
                emitter.onNext("Delta");
                emitter.onNext("Epsilon");
                throw new Exception("test");
            } catch (Exception e) {
                emitter.onError(e);
            }
        }).subscribe(s -> System.out.println("Received " + s), Throwable::printStackTrace);

    }

    @Test
    public void 단순옵져버블_Exception_withOutTryCatch(){
        Observable.create(emitter -> {
                emitter.onNext("Alpha");
                emitter.onNext("Beta");
                emitter.onNext("Gamma");
                emitter.onNext("Delta");
                emitter.onNext("Epsilon");
                throw new Exception("test");
        }).subscribe(s -> System.out.println("Received " + s), Throwable::printStackTrace);

    }

    @Test
    public void 단순옵저버블_맵과필터조합(){
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .map(String::length)
                .filter(i -> i >= 5)
                .subscribe(s-> System.out.println("Received "+ s), Throwable::printStackTrace);
    }

    @Test
    public void 옵저버구현_람다없이() {
        Observable<String> observable = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observer<Integer> observer = new Observer<>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer s) {
                System.out.println("Received " + s);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done");
            }
        };

        observable.map(String::length)
                .filter(i -> i >= 5)
                .subscribe(observer);

    }

    @Test
    public void 옵저버구현_람다포함() {
        Consumer<Integer> onNext =  it -> System.out.println("Received " + it);
        Consumer<Throwable> onError = Throwable::printStackTrace;
        Action onComplete = () -> System.out.println("Done");
        Function<String, Integer> mapper = String::length;
        Predicate<Integer> filter = i -> i >= 5;

        Observable<String> observable = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        observable.map(mapper)
                .filter(filter)
                .subscribe(onNext, onError, onComplete);
    }

    @Test
    public void 콜드_옵저버블() {
        Observable<String> observable =
                Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        Consumer<String> observer1 = it -> System.out.println("Observer 1 Received " + it);
        Consumer<Integer> observer2 = it -> System.out.println("Observer 2 Received " + it);

        observable.subscribe(observer1);
        observable.map(String::length).subscribe(observer2);
    }

    @Test
    public void 커넥터블_옵저버블() {
        ConnectableObservable<String> observable =
                Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon").publish();

        Consumer<String> observer1 = it-> System.out.println("Observer 1 Received " + it);
        Consumer<Integer> observer2 = it-> System.out.println("Observer 1 Received " + it);

        observable.subscribe(observer1);
        observable.map(String::length).subscribe(observer2);

        System.out.println("Already subscribe 2 observers. \nAfter connect \n");

        observable.connect();
    }

    @Test
    public void 레인지_옵저버블() {
        Observable.range(1, 10).subscribe(it-> System.out.println("Received " + it));
        Observable.range(5, 10).subscribe(it-> System.out.println("Received " + it));
    }

    @Test
    public void 인터벌_옵저버블() throws InterruptedException {
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(s-> System.out.println("Received " + s));
        Thread.sleep(5000);
    }

    @Test
    public void 인터벌_두개의콜드옵저버() throws InterruptedException {
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);
        observable.subscribe(s-> System.out.println("Observer1 :" + s));
        Thread.sleep(5000);
        observable.subscribe(s-> System.out.println("Observer2 :" + s));
        Thread.sleep(5000);
    }

    @Test
    public void 인터벌_두개의핫옵저버() throws InterruptedException {
        ConnectableObservable<Long> observable =
                ConnectableObservable.interval(1, TimeUnit.SECONDS).publish();

        observable.subscribe(s-> System.out.println("Observer1 :" + s));
        observable.connect();
        Thread.sleep(5000);
        observable.subscribe(s-> System.out.println("Observer2 :" + s));
        Thread.sleep(5000);
    }

    @Test
    public void 엠티_옵저버블() {
        Observable<Object> observable = Observable.empty();

        observable.subscribe(System.out::println, Throwable::printStackTrace, ()-> System.out.println("Done"));
    }

    @Test
    public void 네버_옵져버블() throws InterruptedException {
        Observable<Object> observable = Observable.never();
        observable.subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Done"));

        Thread.sleep(5000);
    }

    @Test
    public void 에러_옵저버블() {
        Observable<Object> observerble = Observable.error(new Exception("Error Observable test"));
        observerble.subscribe(
                i -> System.out.println("RECEIVED: " + i),
                Throwable::printStackTrace,
                () -> System.out.println("Done"));
    }

    @Test
    public void 레인지변경() {
        int start = 1;
        Observable<Integer> observable = Observable.range(start, count);

        observable.subscribe(s -> System.out.println("Range 1:" + s));

        count = 10;

        observable.subscribe(s -> System.out.println("Range 2:" + s));

    }

    @Test
    public void 레인지변경_defer(){
        int start = 0;
        Observable<Integer> observable = Observable.defer(() -> Observable.range(start, count));

        observable.subscribe(it-> System.out.println("Range 1:" + it));
        count = 10;
        observable.subscribe(it-> System.out.println("Range 2:" + it));
    }

    @Test
    public void 옵저버블_프롬콜러블(){  //1/0을 펑터로 보내면 수행을 인자로 밀어넣게 되어 그 줄에서 바로 상수로 평가되지 않는다.
//        Observable<Integer> just = Observable.just(1 / 0);
//        just.subscribe(s-> System.out.println("Just :" + s),
//                e -> System.out.println("Error Captured: " + e));

        Observable<Integer> fromCallable = Observable.fromCallable(() -> 1 / 0);
        fromCallable.subscribe(s-> System.out.println("FromCallable :" + s),
                e -> System.out.println("Error Captured: " + e));
    }
}
