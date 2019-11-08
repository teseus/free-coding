package com.example.demo.monad;

import java.util.Optional;
import java.util.function.BiFunction;

public class MyMonadTest {

    public void test(){
        Optional<Integer> oa = Optional.of(1);
        Optional<Integer> ob = Optional.of(2);
        Optional<String> sa = Optional.of("a");
        Optional<String> sb = Optional.of("b");

        BiFunction<Integer, Integer, Integer> plus = (x,y) -> x+y;
        BiFunction<Integer, Integer, Integer> multi = (x,y) -> x*y;
        BiFunction<String, String, String> concat = (x,y) -> x+y;

        System.out.println("result =" + addTwoOptionals(oa, ob));

        System.out.println("result =" + calcTowOpt(plus, oa, ob));
        System.out.println("result =" + calcTowOpt(multi, oa, ob));
        System.out.println("result =" + calcTowOpt(concat, sa, sb));
    }

    private <A, B, R> Optional<R> calcTowOpt(BiFunction<A,B,R> biFunction, Optional<A> oa, Optional<B> ob) {
        Optional<R> r = oa.flatMap(a -> ob.map(b -> biFunction.apply(a, b)));
        return r;
        //return oa.flatMap(a->ob.map(b->biFunction.apply(a,b)));
    }

    private Optional<Integer> addTwoOptionals(Optional<Integer> oa, Optional<Integer> ob) {
        if(oa.isPresent() && ob.isPresent()){
            return Optional.of(oa.get() + ob.get());
        }

        return Optional.empty();
    }


    public static void main(String[] args) {
        MyMonadTest a = new MyMonadTest();
        a.test();
    }

}
