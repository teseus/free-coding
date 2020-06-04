package com.teseus.inaction.ch7

import java.math.BigDecimal

data class Point(val x: Long, val y: Long)

operator fun Point.plus(point: Point): Point = Point(x + point.x, y + point.y)

operator fun Point.times(scale: Double): Point = Point(x*scale.toLong(), y*scale.toLong())

operator fun Char.times(num: Int) : String = toString().repeat(num)

operator fun BigDecimal.inc()  = this + BigDecimal.ONE

fun main(){
    val point1 = Point(1L, 2L)
    var point11 = Point(1L, 2L)
    val point2 = Point(2L, 1L)
    val point3 = point1 + point2
    println(point3)

    point11 += point2

    println(point1)

    println(point1 * 3.0)

    println('A' * 3)

    var zero = BigDecimal.ZERO
    println(zero++)
    println(++zero)

}