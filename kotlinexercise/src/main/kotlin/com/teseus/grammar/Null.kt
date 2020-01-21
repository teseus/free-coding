package com.teseus.grammar

fun main() {
    var a:String? = "test"
    println(a?.toUpperCase())
    println((a?:"default").toUpperCase())
    println(a!!.toUpperCase())

    a?.run {
        println(toUpperCase())
        println(toLowerCase())
    }

    var b:String? = null
    println(b?.toUpperCase())
    println((b?:"default").toUpperCase())
    println(b!!.toUpperCase())

}