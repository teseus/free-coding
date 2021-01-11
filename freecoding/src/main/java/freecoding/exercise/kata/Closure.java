package freecoding.exercise.kata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class Closure {
    @Getter
    private int j = 1;

    public static void main(String[] args) {
        final IntStream stream = Arrays.stream(new int[]{1, 2, 3, 4});

        final Closure me = new Closure();

        "a".hashCode();

        final IntStream calced = me.calc(stream, new Int(3));
        calced.forEach(System.out::println);
    }

    private IntStream calc(IntStream stream, Int i) {
        i.setValue(2);
        return stream.map(it->it* i.value * getJ());
    }

    @AllArgsConstructor
    private static class Int {
        @Setter
        private int value;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Int anInt = (Int) o;
            return value == anInt.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
