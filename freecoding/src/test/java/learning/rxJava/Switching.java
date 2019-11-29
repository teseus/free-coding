package learning.rxJava;

import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class Switching {
    @Test
    public void simple_concatMap(){
        Observable<String> items = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon",
                "Zeta", "Eta", "Theta", "Iota");

        Observable<String> processingString = items.concatMap(s -> Observable.just(s)
                .delay(MyUtils.randomSleepTime(), TimeUnit.MILLISECONDS));

        processingString.subscribe(System.out::println);

        MyUtils.sleep(20000);
    }

    @Test
    public void switch_concatMap(){
        Observable<String> items = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon",
                "Zeta", "Eta", "Theta", "Iota")
                .doOnDispose(() -> System.out.println("Source Disposing! Starting next"));

        Observable<String> processingStringMap = items.concatMap(i ->
                Observable.just(i)
                        .delay(MyUtils.randomSleepTime(), TimeUnit.MILLISECONDS))
                .doOnDispose(() -> System.out.println("Disposing! Starting next"));

//        System.out.println("Just Number by interval 5");
//        Disposable dispose = Observable.interval(5, TimeUnit.SECONDS)
//                .subscribe(System.out::println);
//
//        MyUtils.sleep(20000);
//        dispose.dispose();
//
//        System.out.println("switch origianl observable");
//        Observable.interval(5, TimeUnit.SECONDS)
//                .switchMap(i-> items)
//                .subscribe(System.out::println);
//
//        MyUtils.sleep(20000);

        System.out.println("switch processingString observable");
        Observable.interval(5, TimeUnit.SECONDS)
                .switchMap(i-> processingStringMap)
                .subscribe(System.out::println);

        MyUtils.sleep(20000);
    }
}
