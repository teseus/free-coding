package freecoding.exercise.rxjava.observable;


import io.reactivex.Flowable;

public class TestRx {
    public static void main(String[] args) {
        Flowable.just("Hello World!", "Hello teseus").subscribe(System.out::println);
    }
}