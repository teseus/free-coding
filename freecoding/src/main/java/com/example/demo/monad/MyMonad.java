package com.example.demo.monad;

import lombok.ToString;

import java.util.function.Function;

@ToString
public class MyMonad<T> {
    private T value;

    private MyMonad(T value){
        this.value = value;
    }

    public static <T> MyMonad<T> of(T value){
        return new MyMonad(value);
    }

    public <R> MyMonad<R> map(Function<T,R> function){
        return MyMonad.of(function.apply(value));
    }

    public <R> MyMonad<R> flatMap(Function<T,MyMonad<R>> function){
        return function.apply(value);
    }

    public MyMonad<Integer> incr(Integer value){
        return MyMonad.of(value+1);
    }

    public static void main(String[] args) {
        MyMonad<Integer> a = MyMonad.of(1);
        System.out.println("the result = " + a.map(it -> it + 1));
        System.out.println("ths flatmap result = " + a.flatMap(a::incr).flatMap(a::incr));
    }
}