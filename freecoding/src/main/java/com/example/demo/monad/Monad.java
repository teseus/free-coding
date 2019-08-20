package com.example.demo.monad;

import java.util.function.Function;

public class Monad<T> {
    private T value;

    private Monad(T value){
        this.value = value;
    }

    public static <T> Monad<T> of(T value){
        return new Monad(value);
    }

    public <R> Monad<R> map(Function<T, R> functor){
        return Monad.of(functor.apply(value));
    }

    public static void main(String[] args) {
        Monad<Integer> a = new Monad<>(1);
        System.out.println(a);

        a.map(it->it+1);
    }

}
