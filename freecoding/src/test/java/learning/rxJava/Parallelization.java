package learning.rxJava;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Parallelization {
    @Test
    public void Parallelize_AllItems_WithFlatMap(){
        Observable.range(1,100)
                .flatMap(i->Observable.just(i)
                        .subscribeOn(Schedulers.computation())
                        .map(MyUtils::intenseCalculation))
                .subscribe(i -> System.out.println("Received " + i +
                        " on thread " + Thread.currentThread().getName()));

        MyUtils.sleep(10000);

    }

    @Test
    public void Parallelize_GroupedItem_ByCoreNumber() {
        int coreCount = 3;//Runtime.getRuntime().availableProcessors();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Observable.range(1,10)
                .groupBy(i->atomicInteger.incrementAndGet()%coreCount)
                .flatMap(grp->grp.subscribeOn(Schedulers.computation())
                        .map(MyUtils::intenseCalculation))
                .subscribe(i -> System.out.println("Received " + i +
                        " on thread " + Thread.currentThread().getName()));

        MyUtils.sleep(20000);
    }

    @Test
    public void Dispose(){
        Disposable d = Observable.interval(1, TimeUnit.SECONDS)
                .doOnDispose(() -> System.out.println("Disposing on thread "
                        + Thread.currentThread().getName()))
                .subscribe(i -> System.out.println("Received : " + i));

        MyUtils.sleep(3000);

        d.dispose();

        MyUtils.sleep(3000);
    }

    @Test
    public void Dispose_WithUnSubscribeOn(){
        Disposable d = Observable.interval(1, TimeUnit.SECONDS)
                .doOnDispose(() -> System.out.println("Disposing on thread "
                        + Thread.currentThread().getName()))
                .unsubscribeOn(Schedulers.io())
                .subscribe(i -> System.out.println("Received : " + i));

        MyUtils.sleep(3000);

        d.dispose();

        MyUtils.sleep(3000);
    }

}
