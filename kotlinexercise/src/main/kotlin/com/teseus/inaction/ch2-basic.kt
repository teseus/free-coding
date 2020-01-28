package com.teseus.inaction

import java.util.*

fun main() {
    usingWhen()
    usingMap()
    usingListWithIndex()
    rangeFun()
    stringCheckWithKeywordIn()
}

fun stringCheckWithKeywordIn() {
    println("====== stringCheckWithKeywordIn ======")
    println("====== test1 ======")
    println("kotlin" in "java".."scala")
    println("====== test2 ======")
    println("kotlin" in setOf("java", "scala"))
}

fun rangeFun() {
    println("range using when")
    rangeUsingWhen()
    println("range not using when")
    rangeNotUsingWhen()
}

fun rangeUsingWhen() {
    println(getRange('x'))
    println(getRange('q'))
    println(getRange('1'))
    println(getRange('한'))
}

fun getRange(c: Char): String = when(c){
    in 'a'..'z', in 'A'..'Z' -> "Alphabetic"
    in '0'..'9' -> "Numeric"
    else -> "Unknown"
}

private fun rangeNotUsingWhen() {
    println(isLetter('q'))
    println(isNotDigit('x'))
}

fun isNotDigit(c: Char): Boolean = c !in '0'..'9'

fun isLetter(c: Char): Boolean = c in 'a'..'z' || c in 'A'..'Z'


fun usingListWithIndex() {
    val arr = arrayListOf("10", "101", "100011")
    for((i, v) in arr. withIndex()){
        println("index:$i = value:$v")
    }
}

fun usingMap() {
    val treeMap = TreeMap<Char, String>()
    for(c in 'A'..'Z'){
        val toBinaryString = Integer.toBinaryString(c.toInt())
        treeMap[c] = toBinaryString
    }

    for((k, v) in treeMap){
        println("$k = $v")
    }
}

private fun usingWhen() {
    for (i in 100 downTo 1 step 2) {
        println(fizzbuzz(i))
    }
}

fun fizzbuzz(value: Int): String = when {
    value % 15 == 0 -> "fizzbuzz"
    value % 3 == 0-> "fizz"
    value % 5 == 0-> "buzz"
    else -> "$value"
}
