package com.teseus.grammar


fun main(args: Array<String>){
    println(getMixColor(Color.RED, Color.YELLO))
    println(getMixColor(Color.YELLO, Color.RED))
    println(getMixColor(Color.RED, Color.ORANGE))
}

fun getMixColor(c1: Color, c2: Color): Color = when(setOf(c1, c2)){
    setOf(Color.RED, Color.YELLO) -> Color.ORANGE
    else -> throw IllegalStateException("dirty color")
}

enum class Color{
    RED,
    YELLO,
    ORANGE
}