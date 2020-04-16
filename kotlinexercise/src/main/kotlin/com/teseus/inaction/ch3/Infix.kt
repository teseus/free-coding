package com.teseus.inaction.ch3


infix fun Any.toto(other: Any) = Pair(this, other)

fun main(){
    val map = mapOf(1 to "one", 7 toto "seven", 53.toto("fiftythree"))
    println(map)

    val (number, name) = 1 toto "one"
}