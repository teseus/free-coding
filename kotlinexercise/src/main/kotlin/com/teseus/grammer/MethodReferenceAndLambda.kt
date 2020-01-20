package com.teseus.grammer

fun main() {
    println("Hello, world!!!")
    b(::a)
    b({println("2. calling a with $it")})
    b{println("3. calling a with $it")}

    val c:(String)->Unit = {println("4. calling a with $it")}
    b(c)
    val d = {it:String -> println("5. calling a with $it")}
    b(d)
}

fun a(msg:String) {
    println("1. calling a with $msg")
}

fun b(function:(String)->Unit) {
    function("calling b")
}