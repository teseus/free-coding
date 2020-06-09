package com.teseus.inaction.ch8

fun twoAndThree(operation: (Int, Int) -> Int){
    println(operation(1, 2))
}
fun main() {
    twoAndThree{x,y -> x + y}
    twoAndThree{x,y -> x * y}
}