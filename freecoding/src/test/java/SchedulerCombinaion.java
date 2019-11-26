import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class SchedulerCombinaion {
    @Test
    public void Scheduler_onInterval(){
        Observable.interval(1, TimeUnit.SECONDS, Schedulers.newThread())
                .subscribe(i -> System.out.println("Received " + i +
                        " on thread " + Thread.currentThread().getName()));

        MyUtils.sleep(5000);
    }

    @Test
    public void Scheduler_Priority(){
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io()) //be ignored
                .subscribeOn(Schedulers.newThread()) //be ignored
                .filter(i->i.length()==5)
                .subscribe(i -> System.out.println("Received " + i +
                        " on thread " + Thread.currentThread().getName()));

        MyUtils.sleep(5000);
    }

    @Test
    public void SubscribeOn_ObserveOn_Combination(){
        Observable.just("WHISKEY/27653/TANGO", "6555/BRAVO", "232352/5675675/FOXTROT")
                .subscribeOn(Schedulers.io())//for upstream
                .flatMap(s->Observable.fromArray(s.split("/")))
                .observeOn(Schedulers.computation())//for downstream
                .filter(s->s.matches("[0-9]+"))
                .map(Integer::valueOf)
                .reduce((total, next)->total+next)
                .subscribe(i -> System.out.println("Received " + i +
                        " on thread " + Thread.currentThread().getName()));

        MyUtils.sleep(1000);
    }

    /*
    This changes when you introduce an observeOn() and say we put it between Operation A and Operation B.
    What happens is after Operation A hands an emission to the observeOn(),
    it will immediately start the next emission and not wait for the downstream to finish the current one,
    including Operation B and the Observer.
    This means that the source and Operation A can produce emissions faster than Operation B and the Observer can consume them.
    This is a classic producer/consumer scenario where the producer is producing emissions faster than the consumer can consume them.
    If this is the case, unprocessed emissions will be queued in observeOn() until the downstream is able to process them.
    But if you have a lot of emissions, you can potentially run into memory issues.
    */
    @Test
    public void SubscribeOn_ObserveOn_Combination2() {
        Observable.just("WHISKEY/27653/TANGO", "6555/BRAVO", "232352/5675675/FOXTROT")
        .subscribeOn(Schedulers.io())
        .doOnNext(s -> System.out.println("Received a Raw Source " + s + " on thread "
                + Thread.currentThread().getName()))
        .observeOn(Schedulers.newThread())
        .flatMap(s->Observable.fromArray(s.split("/")))//자르고
        .doOnNext(s -> System.out.println("Split out " + s + " on thread "
                + Thread.currentThread().getName()))
        .observeOn(Schedulers.computation())
        .filter(s->s.matches("[0-9]+")) //숫자 필터링
        .doOnNext(s -> System.out.println("filtered " + s + " on thread "
                + Thread.currentThread().getName()))
        .map(Integer::valueOf)//숫자로 변경
        .reduce((total,next) -> total+next)//총합
        .doOnSuccess(i -> System.out.println("Reduced Calculated sum " + i + " on thread "
                + Thread.currentThread().getName()))// doOnSuccess
        .observeOn(Schedulers.io())  //the thread which worked for string emition will be reused here
        .map(String::valueOf)//문자열로 변환
        .doOnSuccess(s -> System.out.println("Writing " + s + " to file on thread "
                + Thread.currentThread().getName()))// doOnSuccess
        //찍기
        .subscribe(s -> MyUtils.write(s,"./output.txt"));

        MyUtils.sleep(1000);
    }
}
