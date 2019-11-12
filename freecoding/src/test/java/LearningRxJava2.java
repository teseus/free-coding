import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.ResourceObserver;
import org.junit.Test;

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

}
