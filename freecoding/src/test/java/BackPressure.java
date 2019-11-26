import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BackPressure {
    @Test
    public void understanding_back_pressure_single_thread(){
        Observable.range(1, 999_999_999)
                .map(MyItem::new)
                .subscribe(myItem->{
                    MyUtils.sleep(50);
                    System.out.println("Received MyItem" + myItem.id);
                });
    }

    @Test
    public void understanding_back_pressure_other_thread(){
        Observable.range(1, 999_999_999)
                .map(MyItem::new)
                .observeOn(Schedulers.io())
                .subscribe(myItem->{
                    MyUtils.sleep(50);
                    System.out.println(
                            "Received MyItem" + myItem.id +
                            " Thread Name : " + Thread.currentThread().getName());
                });

        MyUtils.sleep(Long.MAX_VALUE);
    }

    @Test
    public void understanding_backpressure_with_Flowable(){
        Flowable.range(1, 999_999_999)
                .map(MyItem::new)
                .observeOn(Schedulers.io())
                .subscribe(myItem -> {
                    MyUtils.sleep(50);
                    System.out.println(
                            "Received MyItem" + myItem.id +
                                    " Thread Name : " + Thread.currentThread().getName());
                });

        MyUtils.sleep(Long.MAX_VALUE);
    }

    public static final class MyItem{
        private final int id;

        MyItem(int id){
            this.id = id;
            System.out.println("Constructing MyItem " + id
                    + " Thread Name : " + Thread.currentThread().getName());
        }
    }

    @Test
    public void backpressure_with_interval(){
        Flowable.interval(1, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .map(l->MyUtils.intenseCalculation(l))
                .subscribe(System.out::println, Throwable::printStackTrace);

        MyUtils.sleep(Long.MAX_VALUE);
    }

    @Test
    public void flowable_subscriber(){
        Flowable.range(1,10)
                .observeOn(Schedulers.computation())
                .doOnNext(i-> System.out.println("source pushed : " + i))
                .map(i->MyUtils.intenseCalculation(i))
                .subscribe(s-> System.out.println("Received : " + s), Throwable::printStackTrace,
                        ()-> System.out.println("Done"));

        MyUtils.sleep(20000);
    }

    @Test
    public void flow_control_with_subscriber(){
        Flowable.range(1, 1000)
                .doOnNext(i-> System.out.println("Pushed : " + i))
                .observeOn(Schedulers.io())
                .map(MyUtils::intenseCalculation)
                .subscribe(new Subscriber<>() {
                    private Subscription subscription;
                    private AtomicInteger count = new AtomicInteger(0);
                    @Override
                    public void onSubscribe(Subscription s) {
                        System.out.println("start subscription with 40 requests");
                        s.request(40);
                        this.subscription = s;
                    }

                    @Override
                    public void onNext(Integer val) {
                        MyUtils.sleep(50);
                        System.out.println("Received : " + val);
                        if(count.incrementAndGet() % 10 == 0 || count.get() >= 20 ){
                            this.subscription.request(20);
                            System.out.println("change subscription with 20 request");
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Done");
                    }
                });
        MyUtils.sleep(30000);
    }

    @Test
    public void flow_control_with_subscriber2(){
        Flowable.range(1,1000)
                .doOnNext(s -> System.out.println("Source pushed " + s))
                .observeOn(Schedulers.io())
                .map(i -> MyUtils.intenseCalculation(i))
                .subscribe(new Subscriber<Integer>() {
                    Subscription subscription;
                    AtomicInteger count = new AtomicInteger(0);
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        this.subscription = subscription;
                        System.out.println("Requesting 40 items!");
                        subscription.request(40);
                    }
                    @Override
                    public void onNext(Integer s) {
                        MyUtils.sleep(50);
                        System.out.println("Subscriber received " + s);
                        if (count.incrementAndGet() % 20 == 0 && count.get() >= 40)
                            System.out.println("Requesting 20 more!");
                        subscription.request(20);
                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onComplete() {
                        System.out.println("Done!");
                    }
                });
        MyUtils.sleep(20000);
    }
}
