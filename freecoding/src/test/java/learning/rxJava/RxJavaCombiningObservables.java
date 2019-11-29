package learning.rxJava;

import io.reactivex.Observable;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RxJavaCombiningObservables {
    @Test
    public void Merge_Combining1() {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<String> source2 = Observable.just("Zeta", "Eta", "Theta");

        Observable.merge(source1, source2)
                .subscribe(i -> System.out.println(" Received1 :" + i), Throwable::printStackTrace
                        , () -> System.out.println(" Done"));

        source1.mergeWith(source2)
                .subscribe(i -> System.out.println(" Received2 :" + i), Throwable::printStackTrace
                        , () -> System.out.println(" Done"));
    }

    @Test
    public void Merge_Combining2() {
        Observable<String> source1 = Observable.just("Alpha", "Beta");
        Observable<String> source2 = Observable.just("Gamma", "Delta");
        Observable<String> source3 = Observable.just("Epsilon", "Zeta");
        Observable<String> source4 = Observable.just("Eta", "Theta");
        Observable<String> source5 = Observable.just("Iota", "Kappa");

        Observable.mergeArray(source1, source2, source3, source4, source5)
                .subscribe(i -> System.out.println(" Received1 :" + i), Throwable::printStackTrace
                        , () -> System.out.println(" Done"));

        List<Observable<String>> observables = Arrays.asList(source1, source2, source3, source4, source5);
        Observable.merge(observables)
                .subscribe(i -> System.out.println(" Received2 :" + i), Throwable::printStackTrace
                        , () -> System.out.println(" Done"));

    }

    @Test
    public void Merge_Combining() {
        Observable<String> seconds = Observable.interval(1, TimeUnit.SECONDS)
                .map(l -> l + 1)
                .map(l -> "Source 1 : " + l + " Seconds");
        Observable<String> milliseconds = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> (l + 1) * 300)
                .map(l -> "Source 2 : " + l + " Milliseconds");

        Observable.merge(seconds, milliseconds)
                .subscribe(System.out::println);

        MyUtils.sleep(5000);
    }

    @Test
    public void FlatMap_Combining() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .flatMap(s -> Observable.fromArray(s.split("")))
                .subscribe(System.out::println);

        System.out.println("-----------");

        Observable<String> source =
                Observable.just("521934/2342/FOXTROT",
                        "21962/12112/78886/TANGO",
                        "283242/4542/WHISKEY/2348562");

        source.flatMap(s -> Observable.fromArray(s.split("/")))
                .filter(s -> s.matches("[0-9]+"))
                .map(Integer::parseInt)
                .subscribe(System.out::println);

        System.out.println("-----------");
    }

    @Test
    public void FlatMap_WithInterval_Combining() {
        Observable.just(2, 3, 10, 7)
                .flatMap(i -> Observable.interval(i, TimeUnit.SECONDS)
                        .map(i2 -> i + "s interval: " + ((i + 1) * i) + " seconds elapsed"))
                .subscribe(System.out::println);

        MyUtils.sleep(12000);
    }

    @Test
    public void FlatMap_WithInterval_WithEmpty_Combining() {
        Observable.just(2, 0, 3, 10, 7)
                .flatMap(i -> {
                    if (i == 0)
                        return Observable.empty();
                    else
                        return Observable.interval(i, TimeUnit.SECONDS)
                                .map(i2 -> i + "s interval: "
                                        + ((i + 1) * i) + " seconds elapsed");
                }).subscribe(System.out::println);
        MyUtils.sleep(12000);
    }

    @Test
    public void FlatMap_Association(){
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .flatMap(s -> Observable.fromArray(s.split("")),
                        (s,r) -> s + "-" + r)
                .subscribe(System.out::println);
    }

}