@file:JvmName("ProcessTheAnswer")
package com.teseus.inaction.ch8

fun processTheAnswer(f:(Int) -> Int) {
    println(f(10))
}

fun main() {
    processTheAnswer { it * 2 }
}

