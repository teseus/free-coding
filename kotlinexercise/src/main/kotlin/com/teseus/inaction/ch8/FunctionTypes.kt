package com.teseus.inaction.ch8

fun requestFunction(name:String, callBack: (Int, String) -> Unit){
    println(name)
    callBack(1, "string")
}

fun main() {
    requestFunction("A function") {x, y -> println("$x,$y")}
    requestFunction("B function") {x, y -> println("$y, $x")}
}