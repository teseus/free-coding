package com.teseus.inaction.ch8

fun String.filter(predicate: (Char) -> Boolean) : String{
    val sb: StringBuilder = java.lang.StringBuilder()
    for(index in 0 until length) {
        if (predicate(get(index))) {
            sb.append(get(index))
        }
    }
    return sb.toString()
}

fun main() {
    println("ab1c".filter { it in 'a'..'z' })
}