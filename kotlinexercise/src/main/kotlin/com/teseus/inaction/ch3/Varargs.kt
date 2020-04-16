package com.teseus.inaction.ch3

fun main() {
    varargtest(arrayOf("aa","bbb","ccc"))
}

fun varargtest(args : Array<String>) {
    val list = listOf("args : ", *args)
    println(list)
}
