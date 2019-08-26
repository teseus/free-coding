package freecoding.exercise.monad;

import lombok.ToString;

import java.util.Objects;
import java.util.function.Function;

@ToString
public class Monad<T> {
    private T value;

    private Monad(T value){
        this.value = value;
    }

    public static <T> Monad<T> of(T value){
        return new Monad(value);
    }

    public <R> Monad<R> map(Function<T,R> function){
        Function<T, Monad<R>> tMonadFunction = function.andThen(Monad::of);
        return flatMap(tMonadFunction);
    }

    public <R> Monad<R> flatMap(Function<T,Monad<R>> function){
        return function.apply(value);
    }

}
