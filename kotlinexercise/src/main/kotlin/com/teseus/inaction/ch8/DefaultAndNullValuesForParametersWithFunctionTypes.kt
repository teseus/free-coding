package com.teseus.inaction.ch8

fun main() {
    val seasons = listOf("Spring", "Summer", "Fall", "Winter")
    println(seasons.joinToString2())
    println(seasons.joinToString2 { it.toUpperCase() })
}

private fun <E> Collection<E>.joinToString2(
        pre: String = "[", sep: String = ", ", post: String = "]",
        transform: ((E) -> String)? = null): String {
    val sb = StringBuilder(pre)
    for ((idx, ele) in withIndex()) {
        if (idx > 0) sb.append(sep)
        val converted = transform?.invoke(ele) ?: ele.toString()
        sb.append(converted)
    }

    return sb.append(post).toString()
}
