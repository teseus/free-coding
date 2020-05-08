package com.teseus.inaction.ch4

class User constructor(val _nickname: String){
    val nickname: String
    init {
        nickname = _nickname
    }
}

//2nd way
//class User(_nickname: String) {
//    val nickname = _nickname
//}

//3rd way
//class User(val nickname:String)

fun main() {

}