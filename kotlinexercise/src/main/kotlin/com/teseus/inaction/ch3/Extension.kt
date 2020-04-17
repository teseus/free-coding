package com.teseus.inaction.ch3

val String.lastChar : Char
    get() = get(length - 1)


var StringBuilder.lastChar : Char
    get() = get(length - 1)
    set(value : Char) {
        this.setCharAt(length - 1, value)
    }

fun main() {
    println("가나다라".lastChar)

    val sb:StringBuilder = StringBuilder("가나다라?")
    println(sb.lastChar)
    sb.lastChar = '!'
    println(sb)
}