package learning.rxJava;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadPoolWithScheduler {
    @Test
    public void Scheduler_NewThread() {

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.newThread())
                .subscribe(i->log.debug(i));

        log.debug("main thread");
    }

    @Test
    public void Scheduler_ExecutorService(){
        int threadCount = 20;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        Scheduler scheduler = Schedulers.from(executorService);
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(scheduler)
                .doFinally(executorService::shutdown)
                .subscribe(System.out::println);
    }

    @Test
    public void Scheduler_Other_Tread(){
        final Observable<Integer> observable = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(MyUtils::intenseCalculation)
                .map(String::length);

        observable.subscribe(i-> System.out.println("Observer1 : " + i + " Thread Name : " + Thread.currentThread().getName()));
        observable.subscribe(i-> System.out.println("Observer2 : " + i + " Thread Name : " + Thread.currentThread().getName()));

        MyUtils.sleep(10000);
    }

    @Test
    public void Scheduler_Multicast(){
        final Observable<Integer> observable = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(MyUtils::intenseCalculation)
                .map(String::length)
                .publish()
                .autoConnect(2);

        observable.subscribe(i-> System.out.println("Observer1 : " + i + " Thread Name : " + Thread.currentThread().getName()));
        observable.subscribe(i-> System.out.println("Observer2 : " + i + " Thread Name : " + Thread.currentThread().getName()));

        MyUtils.sleep(10000);
    }

    @Test
    public void Scheduler_FromCallable(){
        Observable.fromCallable(()->MyUtils.getResponse("https://api.github.com/users/thomasnield/starred"))
                .doOnSubscribe(i->System.out.println(
                        "Subscribe : " + i +
                        " Thread Name : " + Thread.currentThread().getName()))
                .subscribeOn(Schedulers.io())
                .subscribe(i->System.out.println(
                        "printed : " + i +
                                " Thread Name : " + Thread.currentThread().getName()));
        MyUtils.sleep(10000);
    }

}
