package freecoding.exercise.rxjava.observable;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class CompositeDisposableTester  {
    public static void main(String[] args)  throws InterruptedException {
        CompositeDisposable compositeDisposable = new CompositeDisposable();

        //Create an Single observer
        Disposable disposableSingle = Single.just("Hello World")
                .delay(2, TimeUnit.SECONDS, Schedulers.io())
                .subscribeWith(
                        new DisposableSingleObserver<String>() {
                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onSuccess(String value) {
                                System.out.println(value);
                            }
                        });

        //Create an observer
        Disposable disposableMayBe = Maybe.just("Hi")
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

        compositeDisposable.add(disposableSingle);
        compositeDisposable.add(disposableMayBe);

        //start observing
        compositeDisposable.dispose();
    }
}
