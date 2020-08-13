package com.teseus.inaction.ch5

data class Person(val name: String, val age: Int)

fun main(){
//    simplestLambda()
//    maxBy()
    jointToStringTest()
}



fun simplestLambda() {
    { println("test lambda1") }()
    val function = { println("test lambda2") }
    function()
}

fun jointToStringTest() {
    val people = getPeople()
    println(people.joinToString(separator = " ", transform = {p:Person -> p.name}))
    println(people.joinToString(" ") {p:Person -> p.name})
    println(people.joinToString(" ") {it.name})
}

private fun maxBy() {
    val people = getPeople()

    println(people.maxBy({ p: Person -> p.age })) //original form
    println(people.maxBy({ it.age }))
    println(people.maxBy(){ it.age })
    println(people.maxBy { it.age })
}

private fun getPeople(): List<Person> {
    return listOf<Person>(
            Person("홍길동", 30),
            Person("철수", 13),
            Person("영희", 13),
            Person("아무개", 40)
    )
}