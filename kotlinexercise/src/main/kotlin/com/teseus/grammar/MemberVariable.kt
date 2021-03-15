package com.teseus.grammar

fun main(args: Array<String>) {
    val mem = MemberVariable(1)

    mem.num2 = 3
    println(mem)
}

class Address {
    var name: String = "Holmes, Sherlock"
    var street: String = "Baker"
    var city: String = "London"
    var state: String? = null
    var zip: String = "123456"
}

class MemberVariable(private var num: Long)  {
    var num2: Long = 1
    override fun toString(): String {
        return "MemberVariable(num=$num, num2=$num2)"
    }
}