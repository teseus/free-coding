package freecoding.exercise.monad;


public class MonadTest {

    public static void main(String[] args) {
        MonadTest monadTest = new MonadTest();
        monadTest.test();
    }

    private static Monad<Integer> multiply(Integer it) {
        return Monad.of(it * 2);
    }

    private void test() {

        Monad<Integer> a = Monad.of(1);

        System.out.println(a.map(it->it+1).flatMap(MonadTest::multiply).map(it->it-1));
        System.out.println(a.map(MonadTest::incr));
        System.out.println(a.flatMap(MonadTest::incr).flatMap(MonadTest::multi));
    }

    public static Monad<Integer> incr(Integer i){
        return Monad.of(i+1);
    }

    public static Monad<Integer> multi(Integer i){
        return Monad.of(i*2);
    }

}
