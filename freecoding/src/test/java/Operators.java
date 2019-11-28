import io.reactivex.Observable;
import io.reactivex.ObservableOperator;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableObserver;
import org.junit.Test;

public class Operators {
    @Test
    public void implementing_observable_operator(){
        Observable.range(1,5)
                .lift(doOnEmpry(()-> System.out.println("Operation 1 Empty")))
                .subscribe(v-> System.out.println("Operation 1 : " + v));

        Observable.<Integer>empty()
                .lift(doOnEmpry(()-> System.out.println("Operation 2 Empty")))
                .subscribe(v-> System.out.println("Operation 2 : " + v));
    }

    public static <T> ObservableOperator<T, T> doOnEmpry(Action action){
        return observer -> new DisposableObserver<T>() {
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
                if(isEmpty){
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
}
