package com.teseus.grammar

import kotlinx.coroutines.*

fun main() {
    asyncAndLaunch()
    println("============================")
    asyncCancel()
    println("============================")
    testWithTimeoutOrNull()
}

fun testWithTimeoutOrNull() {
    runBlocking {
        val returned = withTimeoutOrNull(50) {
            for (i in 1..1000) {
                println("running $i")
                delay(10)
            }
            "The end"
        }
        println(returned?:"null returned")
    }
}

fun asyncCancel(){
    runBlocking {
        val async = async {
            for (i in 1..10) {
                printHello()
                delay(100)
            }
        }
        delay(300)
        println("async 취소")
        async.cancel()

    }
}

internal fun asyncAndLaunch() {
    runBlocking {
        val async = async {
            for (i in 1..10) {
                println("async loop:$i")
                delay(100)
            }
            100
        }

        val launch = launch {
            for (j in 1..15) {
                println("launch loop:$j")
                delay(100)
            }
        }
        println("async 대기중")
        val ret = async.await()
        println("async 결과 $ret")

        println("join 대기중")
        launch.join()
        println("join 완료")
    }
}