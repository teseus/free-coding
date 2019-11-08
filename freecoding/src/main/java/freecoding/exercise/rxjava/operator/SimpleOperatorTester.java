package freecoding.exercise.rxjava.operator;

import io.reactivex.rxjava3.core.Observable;

public class SimpleOperatorTester {
    public static void main(String[] args) {
        String[] letters = {"a", "b", "c", "d", "e", "f", "g"};
        final StringBuilder result = new StringBuilder();
        Observable<String> observable = Observable.fromArray(letters);
        observable
                .map(String::toUpperCase)
                .subscribe( letter -> result.append(letter));
        System.out.println(result);
    }
}
