package com.example.demo.monad;


public class MonadTest {

    public static void main(String[] args) {
        MonadTest monadTest = new MonadTest();
        monadTest.test();
    }

    private void test() {

        Wrap<Integer> a = Wrap.of(1);          // Wrap(value = 1)

        Wrap<Integer> b = a.map(i->i+9);       // Wrap(value = 10)

        Wrap<Integer> c = b.map(i->i*11);      // Wrap(value = 110)

        a.map(i->i*10).map(i->i+11);           // Wrap(value = 21)

        Wrap<Integer> finalone = a.flatMap(this::inc).flatMap(this::inc);

        System.out.println("final = " +  finalone);
    }

    public Wrap<Integer> inc(Integer x) {
        return Wrap.of(x + 1);
    }

}
