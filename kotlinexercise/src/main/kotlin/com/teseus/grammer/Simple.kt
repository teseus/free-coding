package com.teseus.grammer

fun main() {
    val valInteger: Int = 10
    var varInteger: Int = valInteger

    println("hello teseus ${varInteger}")

    doWhen(1)
    doWhen("teseus")
    doWhen(10000000000)
    doWhen("ax eyes soon will die with his lung cancer")
    doWhen(true)

    printHello()

    nullSafeCall()

    println("elvisOperation = " + elvisOperation(null))

    val developer = Developer("teseus", "kotlin")

    println(developer.name + "," + developer.favorateLanguage)

    assertionNull()
}

fun elvisOperation(elvis: String?): Int {
    return elvis?.length ?: 0
}


fun assertionNull(){
    var test:String? = null
    println(test!!.split(","))
}

fun doWhen(a: Any){
    when(a){
        1->println("input ${a}")
        "teseus"->println("input ${a}")
        is Long -> println("input Long")
        !is String -> println("문자열이 아닙니다.")
        else -> println("안맞는 값입니다.")
    }

    println(doWhenReturnValue(a))

}

fun doWhenReturnValue(a: Any):String {
    var returned = when(a){
        1->"input ${a}"
        "teseus"->"input ${a}"
        is Long -> "input Long"
        !is String -> "문자열이 아닙니다."
        else -> "안맞는 값입니다."
    }

    return returned
}

fun printHello() = println("hello")

fun nullSafeCall() {
    val languages = listOf("korean","english","chinese")
    println(languages.isNotEmpty())

    val nullLanguages:List<String>? = null
    //nullLanguages?.isNotEmpty() XXXXX
    println(nullLanguages?.isNotEmpty()) //OK
}

