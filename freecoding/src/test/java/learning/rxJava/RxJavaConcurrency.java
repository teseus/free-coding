package learning.rxJava;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class RxJavaConcurrency {

    @Test
    public void Sequencial_Delay(){
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .map(s->MyUtils.intenseCalculation(s))
                .subscribe(System.out::println);

        Observable.range(1,6)
                .map(s->MyUtils.intenseCalculation(s))
                .subscribe(System.out::println);
    }

    @Test
    public void Parallel_Delay(){
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(s->MyUtils.intenseCalculation(s))
                .subscribe(i->log.debug(""+i));

        Observable.range(1, 6)
                .subscribeOn(Schedulers.computation())
                .map(s->MyUtils.intenseCalculation(s))
                .subscribe(i->log.debug(""+i));

        MyUtils.sleep(20000);
    }

    @Test
    public void Parallel_WithZip() {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(i -> MyUtils.intenseCalculation(i));

        Observable<Integer> source2 = Observable.range(1, 8)
                .subscribeOn(Schedulers.computation())
                .map(i -> MyUtils.intenseCalculation(i));

        Observable.zip(source1, source2, (s1,s2) -> s1 + "-" + s2)
                .subscribe(System.out::println);

        MyUtils.sleep(20000);
    }

    @Test
    public void KeepAlive_Until_OnComplete() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(i -> MyUtils.intenseCalculation(i))
                .blockingSubscribe(System.out::println, Throwable::printStackTrace,
                        () -> System.out.println("Done"));
    }
}
