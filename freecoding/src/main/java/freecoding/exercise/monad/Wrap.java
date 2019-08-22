package freecoding.exercise.monad;

import lombok.Getter;

import java.util.function.Function;

public class Wrap<T>{
    @Getter
    private T value;

    private Wrap(T value){
        this.value = value;
    }

    public static <T> Wrap of(T value){
        return new Wrap(value);
    }

    public <R> Wrap<R> map(Function<T, R> mapper){
        return Wrap.of(mapper.apply(value));
    }

    public <R> Wrap<R> flatMap(Function<T, Wrap<R>> mapper) {
        return mapper.apply(value);
    }

    public static void main(String[] args) {
        Wrap<Integer> a = Wrap.of(1);
        Wrap<Integer> map = a.map(it -> it * it);
        map.map(it->it*2);

        a.map(it->it*it).map(it->it*2);

        Wrap result = a.map(Wrap::stinc);

    }

    public static Wrap<Integer> stinc(Integer x) {
        return Wrap.of(x + 1);
    }

    public Wrap<Integer> inc(Integer x) {
        return Wrap.of(x + 1);
    }
    //public final <R> Wrap<R> flatMap(Function<T, Wrap<R>> mapper){

    public Wrap func1(Integer value){
        Wrap<Integer> a = Wrap.of(1);
        Wrap<Integer> integerWrap = a.flatMap(this::inc);

        Wrap<Wrap<Integer>> map = a.map(this::inc);

        Wrap<Integer> map1 = a.map(it -> it + 1).map(it->it);

        Wrap<Integer> integerWrap1 = a.flatMap(this::inc);

        return a.flatMap(this::inc).map(it->it);
    }

}

