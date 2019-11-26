import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

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
}
