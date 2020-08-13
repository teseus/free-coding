package com.teseus.inaction.ch4

interface User {
    val nickname : String //it's a Abtract Property
}

class PrivateUser(override val nickname : String) : User  //primary constructor property

class SubscribingUser(private val email : String) : User {
    override val nickname : String
        get() = email.substringBefore('@') //cutom getter
}

class FaceBookUser(val accountId : Long) : User {
    override val nickname = getFaceBookUser(accountId) //Property Initializer
}
private fun getFaceBookUser(accountId: Long): String  = "facebook User $accountId"

fun main() {
    println(PrivateUser("teseus@wemakeprice.com").nickname)
    println(SubscribingUser("teseus@wemakeprice.com").nickname)
    println(FaceBookUser(1234).nickname)
}