package freecoding.exercise.monad;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class OptionalTest {

    public static void main(String[] args) {
        Optional<Integer> op1 = Optional.of(1);
        Optional<Integer> op2 = Optional.of(2);

        Optional<Integer> op3 = addOptionals(op1, op2);

        System.out.println("the result = " + op3.get());

        BiFunction<Integer, Integer, Integer> plus = (x,y)->x+y;
        BiFunction<Integer, Integer, Integer> multi = (x,y)->x*y;

        OptionalTest optionalTest = new OptionalTest();
        System.out.println("1. plus compute = " + optionalTest.compute((x,y)->x+y, op1, op2));
        System.out.println("1. plus compute = " + optionalTest.compute(plus, op1, op2));
        System.out.println("2. multi compute = " + optionalTest.compute(multi, op1, op2));

    }

    private static Optional<Integer> addOptionals(Optional<Integer> op1, Optional<Integer> op2) {

        return op1.flatMap(o1->op2.map(o2->o1+o2));

    }

    private <A,B,R> Optional<R> compute(BiFunction<A, B, R> mapper, Optional<A> oa, Optional<B> ob){

        return oa.flatMap(a->ob.map(b->mapper.apply(a,b)));

    }

}
