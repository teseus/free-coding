package com.teseus.grammar

fun main() {
    val a:String? = null
    val b = ""
    val c = " "
    val d = "A"

    println(a.isNullOrEmpty())
    println(b.isNullOrEmpty())
    println(c.isNullOrEmpty())
    println(d.isNullOrEmpty())

    println(a.isNullOrBlank())
    println(b.isNullOrBlank())
    println(c.isNullOrBlank())
    println(d.isNullOrBlank())
}