import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class WindowIng {
    @Test
    public void fixed_size_windowing() {
        Observable.range(1, 50)
                .window(8)
                .flatMapSingle(osvb->
                        osvb.reduce("", (total, next)->
                                        total + (total.equals("")?"":"|") + next
                        ))
                .subscribe(System.out::println);

        System.out.println("===========");

        Observable.range(1, 50)
                .window(2, 3)
                .flatMapSingle(osvb->
                        osvb.reduce("", (total, next)->
                                total + (total.equals("")?"":"|") + next
                        ))
                .subscribe(System.out::println);

    }

    @Test
    public void time_based_widnowing(){
        Disposable disposable = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(i -> (i + 1) * 300)
                .window(1, TimeUnit.SECONDS)
                .flatMapSingle(osvb ->
                        osvb.reduce("", (total, next) ->
                                total + (total.equals("") ? "" : "|") + next
                        ))
                .subscribe(System.out::println);

        MyUtils.sleep(5000);

        disposable.dispose();
        System.out.println("===========");

        Observable<Long> cutOff = Observable.interval(1, TimeUnit.SECONDS);
        Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(i -> (i + 1) * 300)
                .window(cutOff)
                .flatMapSingle(osvb ->
                        osvb.reduce("", (total, next) ->
                                total + (total.equals("") ? "" : "|") + next
                        ))
                .subscribe(System.out::println);

        MyUtils.sleep(5000);
    }

}
