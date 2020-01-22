package com.teseus.grammar

fun main() {
    compareDataClass()

    println("============")

    componentX()
}

private fun componentX() {
    val planes = listOf(DataClass("보잉", "737"),
            DataClass("에어버스", "a320"),
            DataClass("보잉", "737"))

    for ((name, model) in planes) {
        println("name:$name, model:$model")
    }
}

private fun compareDataClass() {
    val a = GeneralClass("보잉", "737")
    println(a == GeneralClass("보잉", "737"))
    println(a.hashCode())
    println(a)

    println("============")

    val b = DataClass("에어버스", "A320")
    println(b == DataClass("에어버스", "A320"))
    println(b.hashCode())
    println(b)

    println(b.copy())
    println(b.copy("Boing"))
    println(b.copy(modelno = "A320D"))
}

data class DataClass(val name: String, val modelno: String)

class GeneralClass(val name: String, val modelno: String)


