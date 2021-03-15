package com.teseus.grammar

fun main(args: Array<String>){
    val rectangle1 = Rectangle(3, 3)
    println(rectangle1.isSquare)
    val rectangle2 = Rectangle(3, 13)
    println(rectangle2.isSquare)
}

class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() = height == width
}
