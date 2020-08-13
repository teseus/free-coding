package com.teseus.inaction.ch8

fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for(index in 0 until length) {
        val char = get(index)
        if (predicate(char)) {
            sb.append(char)
        }
    }
    return sb.toString()
}

fun main(){
    println("a1b2c3".filter { it in 'a'..'z' })
}