package com.teseus.grammar

import java.util.*

fun main(args: Array<String>){
    val rectangle1 = Rectangle(3, 3)
    println(rectangle1.isSquare)
    val rectangle2 = Rectangle(3, 13)
    println(rectangle2.isSquare)

    val random = Random()
    val rectangle3 = Rectangle(random.nextInt(), random.nextInt())
    println(rectangle3)
    println(rectangle3.isSquare)
}

class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() = height == width

    override fun toString(): String {
        return "width:${width}, height:${height}"
    }

}
