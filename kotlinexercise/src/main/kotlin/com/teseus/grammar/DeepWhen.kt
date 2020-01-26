package com.teseus.grammar

import java.lang.IllegalArgumentException

fun main() {
    println(evalu(Sum(Sum(Num(1),Num(2)),Sum(Num(3),Num(4)))))
}

fun evalu(e: Expr): Int = when (e) {
    is Num -> {
        println("Num:${e.value}")
        e.value
    }
    is Sum -> {
        val left = evalu(e.left)
        val right = evalu(e.right)
        println("Sum($left + $right)")
        left + right
    }
    else -> throw IllegalArgumentException()
}

interface Expr
class Sum(val left: Expr,val right: Expr):Expr
class Num(val value: Int): Expr



