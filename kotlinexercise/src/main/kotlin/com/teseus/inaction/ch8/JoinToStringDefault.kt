package com.teseus.inaction.ch8


fun main() {
    val seasons = listOf("Spring", "Summer", "Fall", "Winter")
    val numbers = listOf(1,2,3,4,5,6)
    println(seasons.joinToString1(pre="{", post="}", sep = ";", transform = {it.toString()}))
    println(numbers.joinToString1())
    println(numbers.joinToString1{(it*2).toString()})
    println(seasons.joinToString1 { it.toUpperCase() })
}

private fun <E> Collection<E>.joinToString1(pre:String = "[", sep:String = ",", post:String = "]",
                                            transform:(E) -> String = {it.toString()}): String {
    val sb = StringBuilder(pre)
    for ((i, e) in this.withIndex()) {
        if(i>0) sb.append(sep)
        sb.append(transform(e))
    }
    return sb.append(post).toString()
}
