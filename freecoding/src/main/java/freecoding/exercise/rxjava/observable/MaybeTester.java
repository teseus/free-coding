package freecoding.exercise.rxjava.observable;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MaybeTester {
    public static void main(String[] args) throws InterruptedException {
        //Create an observer
        Disposable disposable = Maybe.just("Hello World")
                .delay(2, TimeUnit.SECONDS, Schedulers.io())
                .subscribeWith(new DisposableMaybeObserver<String>() {
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onSuccess(String value) {
                        System.out.println(value);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Done!");
                    }
                });
        Thread.sleep(3000);
        //start observing
        disposable.dispose();
    }
}