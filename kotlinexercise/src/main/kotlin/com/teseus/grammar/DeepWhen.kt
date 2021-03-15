package com.teseus.grammar

fun main() {
    println(evalu(Sum(Sum(Num(1),Num(2)),Sum(Num(3),Num(4)))))
}

fun evalu(e: Expr): Int = when(e) {
    is Num -> {
        e.value
    }
    is Sum -> {
        evalu(e.left) + evalu(e.right)
    }
    else -> throw IllegalArgumentException()
}

interface Expr
class Sum(val left: Expr,val right: Expr):Expr
class Num(val value: Int): Expr



