package learning.rxJava;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class RxJavaCombiningObservables2 {
    @Test
    public void Concat_Combining1() {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<String> source2 = Observable.just("Zeta", "Eta", "Theta");

        Observable.concat(source1, source2)
                .subscribe(i -> System.out.println("Received 1 : " + i), Throwable::printStackTrace,
                        () -> System.out.println("Done"));

        source1.concatWith(source2)
                .subscribe(i -> System.out.println("Received 2 : " + i), Throwable::printStackTrace,
                        () -> System.out.println("Done"));
    }

    @Test
    public void Concat_Combining2() {
        Observable<String> source1 = Observable.interval(1, TimeUnit.SECONDS)
                .take(2)
                .map(l -> l + 1)
                .map(l -> "Received 1: " + l + " Seconds");

        Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> (l + 1) * 300)
                .map(l -> "Received 2: " + l + " Milliseconds");

        //compare to merge
        //Observable.concat(source1, source2).subscribe(System.out::println);

        Observable.concat(source1, source2)
                .subscribe(i -> System.out.println("Received : " + i), Throwable::printStackTrace,
                        () -> System.out.println("Done"));

        MyUtils.sleep(5000);
    }

    @Test
    public void ConcatMap_Combining3(){
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        source1.concatMap(l->Observable.fromArray(l.split("")))
                .subscribe(i -> System.out.println("Received : " + i), Throwable::printStackTrace,
                        () -> System.out.println("Done"));
        //You will likely want to use flatMap() instead, and we will see it used in concurrency examples in Chapter 6, Concurrency and Parallelization.
    }

    @Test
    public void Ambiguous_Combining(){
        Observable<String> source1 = Observable.interval(1, TimeUnit.SECONDS)
                .take(2)
                .map(l -> l + 1)
                .map(l -> "Source1 (" + l + ")Seconds");

        Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> (l + 1) * 300)
                .map(l -> "Source2 (" + l + ")MilliSeconds");

        Observable.amb(Arrays.asList(source1, source2))
                .subscribe(i -> System.out.println("Received : " + i), Throwable::printStackTrace,
                        () -> System.out.println("Done"));

        MyUtils.sleep(5000);
    }

    @Test
    public void Zip_Combining1(){
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<Integer> source2 = Observable.range(1, 6);

        Observable.zip(source1, source2, (s,i)->s + "-" + i)
                .subscribe(i -> System.out.println("Received : " + i), Throwable::printStackTrace,
                        () -> System.out.println("Done"));
    }

    @Test
    public void Zip_Combining2(){
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<Long> source2 = Observable.interval(1, TimeUnit.SECONDS);

        Observable.zip(source1, source2, (s,l) -> s)
                .subscribe(i -> System.out.println("Received : " + i + " at " + LocalDateTime.now()), Throwable::printStackTrace,
                        () -> System.out.println("Done"));

        MyUtils.sleep(6000);
    }

    @Test
    public void CombineLatest_Combining(){
        Observable<Long> source1 = Observable.interval(300, TimeUnit.MILLISECONDS);
        Observable<Long> source2 = Observable.interval(1, TimeUnit.SECONDS);

        Observable.combineLatest(source1, source2, (s1, s2) -> "Source1(" + (s1+1) + "), Source2(" + (s2+1) + ")")
                .subscribe(i -> System.out.println("Received : " + i), Throwable::printStackTrace,
                        () -> System.out.println("Done"));

        MyUtils.sleep(3000);
    }

    @Test
    public void CombineLatestWith_Combining() {

        Observable<Long> interval = Observable.interval(300, TimeUnit.MILLISECONDS);
        Observable<Long> interval1 = Observable.interval(1, TimeUnit.SECONDS);

        interval1.withLatestFrom(interval, (l1,l2) -> "SOURCE 2: " + l1 + "  SOURCE 1: " + l2)
                .subscribe(i -> System.out.println("Received : " + i), Throwable::printStackTrace,
                        () -> System.out.println("Done"));

        MyUtils.sleep(3000);
    }

    @Test
    public void Grouping_Combining() {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<GroupedObservable<Integer, String>> byLengths = source1.groupBy(String::length);

        byLengths.flatMapSingle(grp->grp.toList())
                .subscribe(i -> System.out.println("Received 1 : " + i), Throwable::printStackTrace,
                        () -> System.out.println("Done"));

        byLengths.flatMapSingle(grp->
                grp.reduce("",(x,y) -> x.equals("")?y:x + "," + y))
                .subscribe(i -> System.out.println("Received 2 : " + i), Throwable::printStackTrace,
                        () -> System.out.println("Done"));

    }
}