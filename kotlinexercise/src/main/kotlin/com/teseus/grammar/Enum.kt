package com.teseus.grammar

fun main() {
    val eating:MyState = MyState.EATING
    println("${eating} 자냐? ${eating.isSleeping()}")

    val sleeping:MyState = MyState.SLEEPING
    println("${sleeping} 자냐? ${sleeping.isSleeping()}")
}

enum class MyState(val msg:String){
    EATING("밥먹는 중"),
    WATCHING("영화 보는 중"),
    SLEEPING("자는 중");

    fun isSleeping(): Boolean = this == SLEEPING
}
