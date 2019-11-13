package freecoding.exercise.rxjava.subject;

import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class PublishSubjectTester {
    public static void main(String[] args) {
        final StringBuilder result1 = new StringBuilder();
        final StringBuilder result2 = new StringBuilder();

        PublishSubject<String> subject = PublishSubject.create();
        Disposable subscribe1 = subject.subscribe(value -> result1.append(value));
        subject.onNext("a");
        subject.onNext("b");
        subject.onNext("c");
        Disposable subscribe2 = subject.subscribe(value -> result2.append(value));
        subject.onNext("d");
        subscribe1.dispose();
        subject.onNext("e");
        subject.onNext("f");
        subscribe2.dispose();
        subject.onNext("g");
        subject.onNext("h");
        subject.onComplete();

        //Output will be abcd
        System.out.println(result1);
        //Output will be d only
        //as subscribed after c item emitted.
        System.out.println(result2);
    }
}
