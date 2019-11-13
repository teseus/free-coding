import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.ResourceObserver;
import org.junit.Test;

import java.util.Comparator;
import java.util.concurrent.TimeUnit;

public class LearningRxJava2 {
    @Test
    public void Single_observable(){
        Single.just("Hello")
                .map(String::length)
                .subscribe(System.out::println, Throwable::printStackTrace);
    }

    @Test
    public void Observable_like_single(){
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .first("Nothing")
                .subscribe(System.out::println, Throwable::printStackTrace);
    }

    @Test
    public void Maybe_Observable(){
        Maybe<Integer> present = Maybe.just(100);
        present.subscribe(i-> System.out.println("Present :" + i), Throwable::printStackTrace,
                ()->System.out.println("Present complete"));

        Maybe<Integer> empty = Maybe.empty();
        empty.subscribe(i-> System.out.println("Empty :" + i), Throwable::printStackTrace,
                ()-> System.out.println("Empty complete"));
    }

    @Test
    public void Observable_like_maybe(){
        Observable<String> observable = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        observable.firstElement()
                .subscribe(s-> System.out.println("When Exist Received :" + s), Throwable::printStackTrace,
                        ()-> System.out.println("When Exist Done"));

        observable = Observable.empty();

        observable.firstElement()
                .subscribe(s-> System.out.println("When empty Received :" + s), Throwable::printStackTrace,
                        ()-> System.out.println("When empty Done"));

    }

    @Test // it does not have  onNext() or onSuccess() to receive emissions, but it does have onError() and onComplete():
    public void Completable_Simple() {
        Completable.fromRunnable(LearningRxJava2::runProcess)
                .subscribe(()-> System.out.println("Completable Done!"), Throwable::printStackTrace);
    }

    public static void runProcess() {
        System.out.println("start");
        sleep(5000);
        System.out.println("end");
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void interval_test(){
        Disposable disposable = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(n -> System.out.println("Received :" + n));

        sleep(5000);

        disposable.dispose();
        System.out.println("disposed");

        sleep(5000);
    }

    @Test
    public void Handling_a_Disposable_within_an_Observer() {
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);

        ResourceObserver<Long> myObserver = new ResourceObserver<>() {
            @Override
            public void onNext(Long aLong) {
                System.out.println("First Received :" + aLong);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("First Done");
            }
        };

        Disposable disposable1 = source.subscribeWith(myObserver);
        Disposable disposable2 = source.subscribe(l -> System.out.println("Second Received :" + l));

        CompositeDisposable compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(disposable1);
        compositeDisposable.add(disposable2);

        sleep(5000);

        System.out.println("Turn off");
        compositeDisposable.dispose();

        sleep(5000);

    }

    @Test
    public void HandlingDisposalwithObservablecreate(){
        Observable<Integer> observable = Observable.create(emitter -> {
            try {
                for (int i = 0; i < 1000; i++) {
                    if (emitter.isDisposed()) {
                        System.out.println("Disposed by obersever");
                        return;
                    }
                    emitter.onNext(i);
                    sleep(10);
                }
                emitter.onComplete();
            } catch (Throwable e) {
                emitter.onError(e);
            }
        });
        System.out.println("Observable Creation Done");

        sleep(100);

        System.out.println("Wait, and start subscrition");

        Disposable disposable = observable.subscribe(
                s -> System.out.println("Received : " + s),
                Throwable::printStackTrace,
                () -> System.out.println("Done"));

        disposable.dispose();
    }

    @Test
    public void Filter_Operator(){
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .filter(it->it.length() != 5)
                .subscribe(System.out::println);
    }

    @Test
    public void Take_Operator(){
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .take(3)
                .subscribe(i-> System.out.println(i), Throwable::printStackTrace
                , () -> System.out.println("Take_Operator Done"));
    }

    @Test
    public void Take_Interval_combi(){
        Observable.interval(300, TimeUnit.MILLISECONDS)
                .take(3)
                .subscribe(i-> System.out.println("Received :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Take_Interval_combi Done"));

        sleep(5000);
    }

    @Test
    public void Skip_Operator() {
        Observable.range(1, 100)
                .skip(90)
                .subscribe(i-> System.out.println("Received :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Skip_Operator Done"));
    }

    @Test
    public void TakeWhile_SkipWhile_Operators(){
        Observable.range(1,100)
                .takeWhile(i->i < 5)
                .subscribe(i-> System.out.println("Received :" + i), Throwable::printStackTrace
                        ,  () -> System.out.println("TakeWhile Done"));



        Observable.range(1,100)
                .skipWhile(i->i < 95)
                .subscribe(i-> System.out.println("Received :" + i), Throwable::printStackTrace
                        ,  () -> System.out.println("skipWhile Done"));

    }

    @Test
    public void Distinct_Operator() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .map(String::length)
                .distinct()
                .subscribe(i-> System.out.println("Received :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Distinct_Operator 1 Done"));

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .distinct(String::length)
                .subscribe(i-> System.out.println("Received :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Distinct_Operator 2 Done"));

    }

    @Test
    public void DistinctUntilChange_Operator(){
        Observable.just(1, 1, 1, 2, 2, 3, 2, 2, 1, 1)
                .distinctUntilChanged()
                .subscribe(i-> System.out.println("DistinctUntilChange_Operator1 :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Take Done"));

        Observable.just("Alpha", "Beta", "Zeta", "Eta", "Gamma", "Delta")
                .distinctUntilChanged(String::length)
                .subscribe(i-> System.out.println("DistinctUntilChange_Operator2 :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Take Done"));

    }

    @Test
    public void ElementAt_Operator(){
        Observable.just("Alpha", "Beta", "Gamma", "Eta", "Epsilon")
                .elementAt(3)
                .subscribe(i-> System.out.println("ElementAt_Operator :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Take Done"));
    }

    @Test
    public void Cast_Operator(){
        Observable.just("Alpha", "Beta", "Gamma", "Eta", "Epsilon")
                .map(o->(Object)o)
                .subscribe(i-> System.out.println("Cast_Operator 1 :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Take Done"));

        Observable.just("Alpha", "Beta", "Gamma", "Eta", "Epsilon")
                .cast(Object.class)
                .subscribe(i-> System.out.println("Cast_Operator 2 :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Take Done"));
    }

    @Test
    public void StartWith_Operator(){
        Observable<String> menu =
                Observable.just("Coffee", "Tea", "Espresso", "Latte");

        //print menu
        menu.startWith("inserted menu")
                .subscribe(System.out::println);

        menu.startWithArray("inserted menu", "---------")
                .subscribe(System.out::println);
    }

    @Test
    public void DefaultIfEmpty_Operator(){
        Observable.just("Alpha", "Beta", "Gamma", "Eta", "Epsilon")
                .filter(s->s.startsWith("z"))
                .defaultIfEmpty("None")
                .subscribe(i-> System.out.println("DefaultIfEmpty_Operator :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Take Done"));
    }

    @Test
    public void SwitchIfEmpty_Operator(){
        Observable.just("Alpha", "Beta", "Gamma", "Eta", "Epsilon")
                .filter(s->s.startsWith("z"))
                .switchIfEmpty(Observable.just("가", "나", "다", "라"))
                .subscribe(i-> System.out.println("SwitchIfEmpty :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Take Done"));
    }

    @Test
    public void Sorted_Operator(){
        Observable.just(3,4,5,1,2,9,8,7)
                .sorted()
                .subscribe(i-> System.out.println("Sorted_Operator1 :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Take Done"));

        Observable.just(3,4,5,1,2,9,8,7)
                .sorted(Comparator.reverseOrder())
                .subscribe(i-> System.out.println("Sorted_Operator2 :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Take Done"));

        Observable.just("Alpha", "Beta", "Gamma", "Eta", "Epsilon")
                .sorted(Comparator.comparingInt(String::length))
                .subscribe(i-> System.out.println("Received :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Take Done"));

    }

    @Test
    public void Delay_Operator(){
        System.out.println("started Delay_Operator");

        Observable.just("Alpha", "Beta", "Gamma", "Eta", "Epsilon")
                .delay(3, TimeUnit.SECONDS)
                .subscribe(i-> System.out.println("Delay_Operator :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Take Done"));

        sleep(5000);

    }

    @Test
    public void Repeat_Operator(){
        System.out.println("started Delay_Operator");

        Observable.just("Alpha", "Beta", "Gamma", "Eta", "Epsilon")
                .repeat(2)
                .subscribe(i-> System.out.println("Repeat_Operator :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Take Done"));

    }

    @Test
    public void Scan_Operator() {
        Observable.just(5,3,7,10,2,14)
                .scan((accumulator, val) -> accumulator + val)
                .subscribe(i-> System.out.println("Scan_Operator :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Take Done"));

        Observable.just("Alpha", "Beta", "Gamma", "Eta", "Epsilon")
                .scan(0, (total, next) -> total + 1)
                .subscribe(i-> System.out.println("Scan_Operator :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Done"));
    }

    @Test
    public void Reduce_Count_operator(){
        Observable.just("Alpha", "Beta", "Gamma", "Eta", "Epsilon")
                .count()
                .subscribe(i-> System.out.println("Reduce_Count_operator :" + i),
                        Throwable::printStackTrace);

    }

    @Test
    public void Reduce_Operator(){
        Observable.just(5,3,7,10,2,14)
                .reduce((a,b)->a+b)
                .subscribe(i-> System.out.println("Reduce_Operator1 :" + i), Throwable::printStackTrace
                        , () -> System.out.println("Done"));

        System.out.println("--");

        Observable.just(5, 3, 7, 10, 2, 14)
                .reduce("", (pre, now) -> pre + (pre.isEmpty() ? "" : ",") + now)
                .subscribe(i -> System.out.println("Reduce_Operator 2:" + i), Throwable::printStackTrace);

        System.out.println("--");

        Observable.just(5, 3, 7, 10, 2, 14)
                .scan("", (pre, now) -> pre + (pre.isEmpty() ? "" : ",") + now)
                .subscribe(i -> System.out.println("Scan_Operator:" + i), Throwable::printStackTrace);
    }
}
