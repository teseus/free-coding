package com.teseus.grammar

fun main() {
    val d = Drink()
    d.swallow()

    val c: Drink = Cola()
    c.swallow()
    // c.say() it causes Error before downcasting.

    val cc = c as Cola
    cc.say()
    c.say()

    if (c is Cola) {
        c.say()
    }
}

open class Drink {
    val name: String = "음료"

    open fun swallow() {
        println("${name}를 마십니다.")
    }
}

class Cola : Drink() {
    private val kind: String = "콜라"

    override fun swallow() {
        println("$name ${kind}를 마십니다.")
    }

    fun say() {
        println("코카콜라 맛있어.")
    }
}

