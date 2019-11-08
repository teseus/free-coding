package freecoding.exercise.rxjava.subject;


import io.reactivex.rxjava3.subjects.AsyncSubject;

public class AsyncSubjectTester {
    public static void main(String[] args) {
        final StringBuilder result1 = new StringBuilder();
        final StringBuilder result2 = new StringBuilder();

        AsyncSubject<String> subject =  AsyncSubject.create();
        subject.subscribe(value -> result1.append(value) );
        subject.onNext("a");
        subject.onNext("b");
        subject.onNext("c");
        subject.subscribe(value -> result2.append(value));
        subject.onNext("d");
        subject.onNext("e");
        subject.onComplete();

        //Output will be e being the last item emitted
        System.out.println(result1);
        //Output will be e being the last item emitted
        System.out.println(result2);
    }
}
