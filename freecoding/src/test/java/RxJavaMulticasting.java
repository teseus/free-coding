import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class RxJavaMulticasting {
    @Test
    public void ColdObservable() {
        Observable<Integer> range = Observable.range(1, 3);
        range.subscribe(i-> System.out.println("Received 1 : " + i));
        range.subscribe(i-> System.out.println("Received 2 : " + i));
    }

    @Test
    public void HotObservable() {
        ConnectableObservable<Integer> publish = Observable.range(1, 3).publish();

        publish.subscribe(i-> System.out.println("Observable 1 : " + i));
        publish.subscribe(i-> System.out.println("Observable 2 : " + i));

        publish.connect();
    }

    @Test
    public void Multicasting_Operators() {
        Observable<Integer> map = Observable.range(1, 3)
                .map(i -> MyUtils.randomInt());

        map.subscribe(i-> System.out.println("Cold Observer 1 :" + i));
        map.subscribe(i-> System.out.println("Cold Observer 2 :" + i));

        ConnectableObservable<Integer> publish = map.publish();
        publish.subscribe(i-> System.out.println("Hot Observer 1 :" + i));
        publish.subscribe(i-> System.out.println("Hot Observer 2 :" + i));

        publish.connect();

        ConnectableObservable<Integer> publish1 = Observable.range(1, 3).publish();
        Observable<Integer> random = publish1.map(i -> MyUtils.randomInt());

        random.subscribe(i-> System.out.println("Hot Random Observer 1 :" + i));
        random.subscribe(i-> System.out.println("Hot Random Observer 2 :" + i));

        publish1.connect();

    }

    @Test
    public void Multicasting_Publish_Combination(){
        ConnectableObservable<Integer> publish = Observable.range(1, 3)
                .map(i -> MyUtils.randomInt()).publish();

        publish.subscribe(i-> System.out.println("Observer 1 :" + i));

        publish.reduce(0,(a,b)->a+b)
                .subscribe(i-> System.out.println("Observer 2 :" + i));

        publish.connect();
    }

    @Test
    public void Multicasting_Publish_AutoConnect_Combination1() {
        Observable<Integer> threeRandoms = Observable.range(1, 3)
                .map(i -> MyUtils.randomInt())
                .publish()
                .autoConnect(2);

        threeRandoms.subscribe(i-> System.out.println("Observable 1 : " + i));
        threeRandoms.reduce(0, (a,b)->a+b).subscribe(i-> System.out.println("Observable 2 : " + i));
        /* below can't be subscribed*/
        threeRandoms.subscribe(i-> System.out.println("Observable 3 : " + i));
    }

    @Test
    public void Multicasting_Publish_AutoConnect_Combination2(){
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS).publish().autoConnect();

        seconds.subscribe(i-> System.out.println("Observer 1 : " + i));

        MyUtils.sleep(3000);

        seconds.subscribe(i-> System.out.println("Observer 2 : " + i));

        MyUtils.sleep(3000);
    }

    //refCount() can be helpful to multicast between multiple Observers but dispose of the upstream connection
    // when no downstream Observers are present anymore.
    @Test
    public void Multicasting_Publish_RefCount(){
        Observable<Long> refCount = Observable.interval(1, TimeUnit.SECONDS).publish().refCount();

        refCount.take(5).subscribe(i-> System.out.println("refCount Observer 1 : " + i));

        MyUtils.sleep(3000);

        refCount.take(2).subscribe(i-> System.out.println("refCount Observer 2 : " + i));

        MyUtils.sleep(3000);

        refCount.subscribe(i-> System.out.println("refCount Observer 3 : " + i));

        MyUtils.sleep(3000);
    }

    @Test
    public void Multicasting_Publish_Share(){
        //=== below as same as above ===
        Observable<Long> share = Observable.interval(1, TimeUnit.SECONDS).share();

        share.take(5).subscribe(i-> System.out.println("share Observer 1 : " + i));

        MyUtils.sleep(3000);

        share.take(2).subscribe(i-> System.out.println("share Observer 2 : " + i));

        MyUtils.sleep(3000);

        share.subscribe(i-> System.out.println("share Observer 3 : " + i));

        MyUtils.sleep(3000);

    }
}
