import io.reactivex.Observable;
import io.reactivex.subjects.*;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

//A Subject makes you can manually call onNext(), onComplete(), and onError() on a Subject,
public class RxJavaSubjects {
    @Test
    public void Vanila_Subject(){
        PublishSubject<String> subject = PublishSubject.create();

        subject.map(String::length)
                .subscribe(System.out::println);

        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma");
        subject.onComplete();
    }

    @Test
    public void Subject_Right_To_Use() {
        Subject<String> subject = PublishSubject.create();
        subject.subscribe(System.out::println);


        Observable<String> source1 = Observable.interval(1, TimeUnit.SECONDS)
                .map(l -> (l + 1) + " seconds");
        Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l-> ((l+1) * 300) + " milliseconds");


        source1.subscribe(subject);
        source2.subscribe(subject);

        MyUtils.sleep(3000);
    }

    @Test
    public void Subject_Bad_To_Use() {
        Subject<String> subject = PublishSubject.create();

        //It doesn't work
        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma");
        subject.onComplete();

        subject.map(String::length)
                .subscribe(System.out::println);

    }

    @Test
    public void Subject_Bad_To_Use_Enhanced() {
        Subject<String> subject = PublishSubject.create();

        subject.map(String::length)
                .subscribe(System.out::println);

        //It works
        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma");
        subject.onComplete();

    }

    @Test
    public void Subject_Serializing_Dueto_It_is_Thread_UnSafe() {
        Subject<String> subject = PublishSubject.<String>create().toSerialized();

        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma");
        subject.onComplete();

        subject.map(String::length)
                .subscribe(System.out::println);

    }

    @Test
    public void Behaviour_Subjuect(){
        BehaviorSubject<String> subject = BehaviorSubject.create();

        subject.subscribe(s-> System.out.println("Observer 1 : " + s));

        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma"); //the last is shared

        subject.subscribe(s-> System.out.println("Observer 2 : " + s));

        subject.onNext("Delta");
        subject.onNext("Epsilon");
        subject.onNext("Zeta");
    }

    @Test
    public void Replay_Subject() {
        ReplaySubject<String> subject = ReplaySubject.<String>create();

        subject.subscribe(s -> System.out.println("Observer 1 " + s));

        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma");
        subject.onComplete();

        subject.subscribe(s -> System.out.println("Observer 2 " + s));
    }

    @Test
    public void Async_Subject() {
        AsyncSubject<String> subject = AsyncSubject.<String>create();

        subject.subscribe(s-> System.out.println("Observer 1 : " +s), Throwable::printStackTrace,
                ()-> System.out.println("Done"));

        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma"); //the last is available
        subject.onComplete();

        subject.subscribe(s-> System.out.println("Observer 2 : " +s), Throwable::printStackTrace,
                ()-> System.out.println("Done"));
    }

    @Test
    public void UniCast_Subject(){
        UnicastSubject<String> subject = UnicastSubject.create();

        Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l->((l+1)*300) + " seconds")
                .subscribe(subject);

        MyUtils.sleep(2000);

        subject.subscribe(s -> System.out.println("Observer 1 : " + s));

        MyUtils.sleep(2000);
    }

    @Test
    public void UniCast_Publish_Combination() {
        UnicastSubject<String> unicastSubject = UnicastSubject.create();

        Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l->((l+1)*300) + " milliseconds")
                .subscribe(unicastSubject);

        MyUtils.sleep(2000);

        //unicastSubject.subscribe(s-> System.out.println("Observer : " +s));

        Observable<String> multicast = unicastSubject.publish().autoConnect();

        multicast.subscribe(i-> System.out.println("Observer 1 : " + i));

        MyUtils.sleep(2000);

        multicast.subscribe(i-> System.out.println("Observer 2 : " + i));

        MyUtils.sleep(1000);

    }
}
