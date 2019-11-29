package learning.rxJava;

import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.ThreadLocalRandom;
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

    @Test
    public void backpressure_strategy(){
        Flowable<Object> source = Flowable.create(emitter -> {
            for (int i = 0; i <= 1000; i++) {
                if (emitter.isCancelled())
                    return;
                emitter.onNext(i);
            }
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER); //Queues up emissions in an unbounded queue until the downstream is able to consume them, but can cause an OutOfMemoryError if the queue gets too large.
        Disposable dispose = source.observeOn(Schedulers.io())
                .subscribe(System.out::println);

        MyUtils.sleep(1000);
        dispose.dispose();
        System.out.println("==============");

        Flowable<Object> source1 = Flowable.create(emitter -> {
            for (int i = 0; i <= 1000; i++) {
                if (emitter.isCancelled())
                    return;
                emitter.onNext(i);
            }
            emitter.onComplete();
        }, BackpressureStrategy.ERROR); //Queues up emissions in an unbounded queue until the downstream is able to consume them, but can cause an OutOfMemoryError if the queue gets too large.
        source1.observeOn(Schedulers.io())
                .subscribe(System.out::println);

        MyUtils.sleep(1000);
    }

    @Test
    public void change_Observable_to_Flowable(){
        Observable<Integer> observable = Observable.range(1, 1000);
        observable.toFlowable(BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .subscribe(System.out::println);

        MyUtils.sleep(10000);
    }

    @Test
    public void associate_Flowable_with_Observable(){
        Flowable<Integer> flowable = Flowable.range(1, 1000).subscribeOn(Schedulers.computation());

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .flatMap(s->flowable.map(i->i+"-"+s).toObservable())
                .subscribe(System.out::println);

        MyUtils.sleep(5000);
    }

    @Test
    public void onBackPressureBuffer(){
        Flowable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureBuffer()
                .observeOn(Schedulers.computation())
                .subscribe(aLong -> {
                    MyUtils.sleep(500);
                    System.out.println(aLong);
                });

        MyUtils.sleep(5000);
    }

    @Test
    public void onBackPressureBuffer_DROP_LATEST(){
        Flowable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureBuffer(10,
                        ()-> System.out.println("buffer overflow!!"),
                        BackpressureOverflowStrategy.DROP_LATEST)
                .observeOn(Schedulers.computation())
                .subscribe(aLong -> {
                    MyUtils.sleep(5);
                    System.out.println(aLong);
                });

        MyUtils.sleep(5000);
    }

    @Test
    public void onBackPressureLatest(){
        Flowable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureLatest()
                .observeOn(Schedulers.io())
                .subscribe(aLong -> {
                    MyUtils.sleep(5);
                    System.out.println(aLong);
                });

        MyUtils.sleep(5000);
    }

    @Test
    public void onBackPressureDrop(){
        Flowable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureDrop(i-> System.out.println("Dropping" + i))
                .observeOn(Schedulers.io())
                .subscribe(aLong -> {
                    MyUtils.sleep(5);
                    System.out.println(aLong);
                });

        MyUtils.sleep(5000);
    }

    @Test
    public void flowable_generate() {
        Flowable.generate(emitter ->
                    emitter.onNext(
                            ThreadLocalRandom.current().nextInt(1, 10000)))
                .subscribeOn(Schedulers.computation())
                .doOnNext(i -> System.out.println("Emitting " + i))
                .observeOn(Schedulers.io())
                .subscribe(i -> {
                    MyUtils.sleep(50);
                    System.out.println("Received " + i);
                });

        MyUtils.sleep(10000);
    }

    @Test
    public void flowable_generate2() {
       reverseGenerator(100, -100)
               .subscribeOn(Schedulers.computation())
               .doOnNext(i->System.out.println("Emitting " + i))
               .observeOn(Schedulers.io())
               .subscribe(i->{
                   MyUtils.sleep(50);
                   System.out.println("Received " + i);
               });

       MyUtils.sleep(50000);
    }

    static Flowable<Integer> reverseGenerator(int max, int min){
        return Flowable.generate(()->new AtomicInteger(max+1),
                (state,emitter) ->{
                    int val = state.decrementAndGet();
                    if(val < min) {
                        emitter.onComplete();
                    }
                    emitter.onNext(val);
                });
    }
}
