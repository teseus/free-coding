package freecoding.exercise.rxjava.operator;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

public class ConnectableOperatorTester {
    public static void main(String[] args) {
        System.out.println("not connect");
        notConnect();
        System.out.println("connect");
        connect();
    }
    private static void notConnect(){
        String[] letters = {"a", "b", "c", "d", "e", "f", "g"};
        final StringBuilder result = new StringBuilder();
//        ConnectableObservable<String> connectable = Observable.fromArray(letters).publish();
        Observable<String> connectable = Observable.fromArray(letters);
        connectable.subscribe(letter -> result.append(letter));
        System.out.println(result.length());
//        connectable.connect();
        System.out.println(result.length());
        System.out.println(result);

    }
    private static void connect(){
        String[] letters = {"a", "b", "c", "d", "e", "f", "g"};
        final StringBuilder result = new StringBuilder();
        ConnectableObservable<String> connectable = Observable.fromArray(letters).publish();
//        Observable<String> connectable = Observable.fromArray(letters);
        connectable.subscribe(letter -> result.append(letter));
        System.out.println(result.length());
        connectable.connect();
        System.out.println(result.length());
        System.out.println(result);
    }

}
