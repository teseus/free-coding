import io.reactivex.Flowable;
import io.reactivex.FlowableOperator;
import io.reactivex.functions.Action;
import io.reactivex.subscribers.DisposableSubscriber;
import org.junit.Test;
import org.reactivestreams.Subscriber;

public class FlowableOperators {

    public static <T> FlowableOperator<T, T> doOnEmpty(Action action){
        return subscriber -> new DisposableSubscriber<T>() {
                    private boolean isEmpty = true;

                    @Override
                    public void onNext(T t) {
                        this.isEmpty = false;
                        subscriber.onNext(t);
                    }

                    @Override
                    public void onError(Throwable t) {
                        subscriber.onError(t);
                    }

                    @Override
                    public void onComplete() {
                        if(isEmpty){
                            try {
                                action.run();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        subscriber.onComplete();
                    }
                };
    }

    @Test
    public void implementing_flowable_operator(){
        Action action = () -> System.out.println("It's emtpy");
        Flowable.range(1, 5)
                .lift(doOnEmpty(action))
                .subscribe(i->System.out.println("Operation 1 : " + i), Throwable::printStackTrace,
                        ()-> System.out.println("First Done"));

        Flowable.<Integer>empty()
                .lift(doOnEmpty(action))
                .subscribe(i->System.out.println("Operation 2 : " + i), Throwable::printStackTrace,
                        ()-> System.out.println("Second Done"));
    }
}
