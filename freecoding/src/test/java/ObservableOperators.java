import io.reactivex.Observable;
import io.reactivex.ObservableOperator;
import io.reactivex.Observer;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableObserver;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ObservableOperators {
    public static <T> ObservableOperator<T, T> doOnEmpry(Action action){
        return observer -> new DisposableObserver<>() {
            boolean isEmpty = true;

            @Override
            public void onNext(T t) {
                isEmpty = false;
                observer.onNext(t);
            }

            @Override
            public void onError(Throwable e) {
                observer.onError(e);
            }

            @Override
            public void onComplete() {
                if (isEmpty) {
                    try {
                        action.run();
                    } catch (Exception e) {
                        onError(e);
                        return;
                    }
                }
                observer.onComplete();
            }
        };
    }

    @Test
    public void implementing_observable_operator(){
        Observable.range(1,5)
                .lift(doOnEmpry(()-> System.out.println("Operation 1 Empty")))
                .subscribe(v-> System.out.println("Operation 1 : " + v));

        Observable.<Integer>empty()
                .lift(doOnEmpry(()-> System.out.println("Operation 2 Empty")))
                .subscribe(v-> System.out.println("Operation 2 : " + v));
    }

    public static <T> ObservableOperator<List<T>, T> toMyList(){
        return observer -> new DisposableObserver<>() {
            private List<T> list = new ArrayList<>();
            @Override
            public void onNext(T t) {
                list.add(t); //add to List, but don't pass anything downstream
            }

            @Override
            public void onError(Throwable e) {
                observer.onError(e);
            }

            @Override
            public void onComplete() {
                observer.onNext(list);
                observer.onComplete();
            }
        };
    }

    @Test
    public void implementing_observer_operator1(){
        Observable.range(1, 5)
                .lift(toMyList())
                .subscribe(i-> System.out.println("Received 1 : " + i));

        Observable.<Integer>empty()
                .lift(toMyList())
                .subscribe(i-> System.out.println("Receive 2 : " + i));
    }



}
