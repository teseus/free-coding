package com.example.demo.monad;

import lombok.ToString;

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

    public <R> Monad<R> map(Function<T, R> functor){
        return flatMap(functor.andThen(it->Monad.of(it)));
    }

    public <R> Monad<R> flatMap(Function<T, Monad<R>> mapper){
        return mapper.apply(value);
    }

    public static Monad<Integer> incr(Integer val){
        return Monad.of(1+val);
    }

    public static void main(String[] args) {
        Function<Integer, Integer> function = it->it+1;

        Monad<Integer> a = new Monad<>(1);
        System.out.println(a);
        System.out.println(a.map(it -> it + 1).map(it -> it + 2));
        System.out.println(a.flatMap(Monad::incr).flatMap(Monad::incr));
    }

}
