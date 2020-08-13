package com.teseus.inaction.ch4

//First way
class User1 constructor(val _nickname: String){
    val nickname: String
    init {
        nickname = _nickname
        println(nickname)
    }
}

//Second way
class User2(_nickname: String) {
    val nickname = _nickname
}

//3rd way
class User3(val nickname:String)

open class Person(val nickname: String)

class TwitterPerson(nickname: String) : Person(nickname)

fun main() {
    //similiar all
    val user1 = User1("user1")
    val user2 = User2("user2")
    val user3 = User3("user3")

    val per : Person = TwitterPerson("Twitter")

    println(per.nickname)
}