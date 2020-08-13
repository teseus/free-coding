package com.teseus.inaction.ch8

enum class Delivery{
    STANDARD, EXPEDITED
}

fun main() {
    val standard = getCalc(Delivery.STANDARD)
    println(standard(Order(3)))

    val expedited = getCalc(Delivery.EXPEDITED)
    println(expedited(Order(3)))
}

fun getCalc(delivery: Delivery): (Order) -> Double {
    if(delivery == Delivery.STANDARD) {
        return { order -> order.num * 1.0 }
    }
    return {order -> order.num * 2.0}
}

data class Order(val num: Int)
