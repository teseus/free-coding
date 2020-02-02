package com.teseus.inaction

import java.lang.StringBuilder

fun main() {
    val abc = listOf("A", "B", "C")
    val numbers = listOf(1,2,3,4)
    println(joinToString(abc, "(", ',', ")"))
    println(joinToString(numbers, "(", ',', ")"))
    println(joinToString(numbers, postfix = ")", prefix = "(", separator = ',' ))

}

fun <T> joinToString(collection: Collection<T>, prefix: String, separator: Char, postfix: String): String {
    val result = StringBuilder(prefix)

    for((idx,value) in collection.withIndex()){
        if(idx != 0) result.append(separator)
        result.append(value)
    }

    result.append(postfix)
    return result.toString()
}
