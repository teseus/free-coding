package effectivejava;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GenericTest {
    @Test
    public void wrong_case1() {
        List<String> strings = new ArrayList<>();
        unsafeAdd(strings, Integer.valueOf(42));
        String s = strings.get(0); // Has compiler-generated cast
    }

    private static void unsafeAdd(List list, Object o) {
        list.add(o); //runtime error
    }

    @Test
    public void correct_wrong_case1(){

    }

    private static void safeAdd(List<?> list, Object o){
        //list.add(o);
    }

}
