package com.teseus.inaction.ch3

class User(val id:Int, val name: String, val address:String)

fun User.validate() {
    fun valid(arg: String){
        if(arg.isEmpty()){
            throw IllegalArgumentException("arument is empty")
        }
    }
    valid(this.name)
    valid(this.address)
}


fun main(){
    val user1 = User(0, "teseus", "seoul")
    user1.validate()
}