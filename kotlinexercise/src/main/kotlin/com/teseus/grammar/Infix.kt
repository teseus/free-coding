package com.teseus.grammar

fun main(){
    infixTest()
    deliveryItem("마이구미")
    deliveryItem("마이구미", 2)
    deliveryItem("마이구미", address = "삼성동")
}

fun infixTest(){
    val ret = 3 multiply 4
    println("infix return = $ret")
}

infix fun Int.multiply(y:Int):Int = this*y

fun deliveryItem(name:String, count:Int = 1, address:String = "강송로"){
    println("$name, $count, $address")
}