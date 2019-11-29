package learning.rxJava;

import io.reactivex.Observable;
import org.junit.Test;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class Buffering {

    @Test
    public void fixed_size_buffering(){
        Observable.range(1,50)
                .buffer(8)
                .subscribe(System.out::println);

        System.out.println("============");

        Observable.range(1,10)
                .buffer(2, 3)
                .subscribe(System.out::println);

        System.out.println("============");

        Observable.range(1,10)
                .buffer(3, 1) //latter skip
                .subscribe(System.out::println);

        System.out.println("============");

        Observable.range(1,10)
                .buffer(3, 1) //latter skip
                .filter(c->c.size() == 3)
                .subscribe(System.out::println);

    }

    @Test
    public void fixed_size_hash_set(){
        Observable.range(1,50)
                .buffer(8, HashSet::new)
                .subscribe(System.out::println);
    }

    @Test
    public void time_based_buffering(){
        Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(i->((i+1)*300))
                .buffer(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);

        MyUtils.sleep(4000);
    }

    @Test
    public void time_based_buffering_limit(){
        Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(i->(i+1)*300)
                .buffer(1, TimeUnit.SECONDS, 2)
                .subscribe(System.out::println);

        MyUtils.sleep(5000);
    }

    @Test
    public void boundary_based_buffering(){
        Observable<Long> cutOffs = Observable.interval(1, TimeUnit.SECONDS);
        Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(i->(i+1)*300)
                .buffer(cutOffs) //he boundary can be any Observable representing any kind of event happening at any time
                .subscribe(System.out::println);

        MyUtils.sleep(5000);
    }
}
