import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class Throttling {
    @Test
    public void concat_interval_observable(){
        //100
        Observable<String> source1 = Observable.interval(100, TimeUnit.MILLISECONDS)
                .map(i -> (i + 1) * 100)
                .map(i -> "Source 1 : " + i)
                .take(10);
        //300
        Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(i -> (i + 1) * 300)
                .map(i -> "Source 2 : " + i)
                .take(3);
        //2000
        Observable<String> source3 = Observable.interval(2000, TimeUnit.MILLISECONDS)
                .map(i -> (i + 1) * 2000)
                .map(i -> "Source 3 : " + i)
                .take(2);

        System.out.println("========= simple concat");

        Observable.concat(source1, source2, source3)
                .subscribe(System.out::println);

        MyUtils.sleep(6000);

        System.out.println("========= throttleLast 1 second");

        Observable.concat(source1, source2, source3)
                .throttleLast(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);

        MyUtils.sleep(6000);

        System.out.println("========= throttleLast 2 seconds");

        Observable.concat(source1, source2, source3)
                .throttleLast(2, TimeUnit.SECONDS)
                .subscribe(System.out::println);

        MyUtils.sleep(6000);

        System.out.println("========= throttleLast 500 MILLISECONDS");

        Observable.concat(source1, source2, source3)
                .throttleLast(500, TimeUnit.MILLISECONDS)
                .subscribe(System.out::println);

        MyUtils.sleep(6000);

        System.out.println("========= throttleFirst 1 SECONDS");

        Observable.concat(source1, source2, source3)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);

        MyUtils.sleep(6000);

        System.out.println("========= throttleWithTimeout 1 SECONDS");

        Observable.concat(source1, source2, source3)
                .throttleWithTimeout(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);

        MyUtils.sleep(6000);

    }
}
