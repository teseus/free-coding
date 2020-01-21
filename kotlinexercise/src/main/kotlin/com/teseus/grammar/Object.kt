package com.teseus.grammar

fun main(){
    var a = FoodPoll("짜장")
    var b = FoodPoll("짬뽕")

    a.vote()
    a.vote()
    a.vote()

    b.vote()
    b.vote()

    println("${a.name} count:${a.count}, total:${FoodPoll.total}")
    println("${b.name} count:${b.count}, total:${FoodPoll.total}")
}

class FoodPoll(val name: String) {
    companion object {
        var total: Int = 0
    }

    var count: Int = 0

    fun vote(){
        total ++
        count ++
    }
}
