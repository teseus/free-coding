package com.teseus.inaction.ch4.p1.p4

interface User{
    val email : String // it should be overridden
    val nickname : String // but, it is inherited
        get() = email.substringBefore('@')
}

class UserImplementation(override val email : String) : User

fun main() {
    println(UserImplementation("teseus@wemakeprice.com").nickname)
}