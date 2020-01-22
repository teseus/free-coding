package com.teseus.grammar

fun main() {
    OuterOne.NestedOne().call()

    val outerOne = OuterOne()
    val innerOne = outerOne.InnerOne()
    innerOne.callInnerOne()
    innerOne.callOuterOne()

    outerOne.name = "Changed Outer One"
    innerOne.callOuterOne()
}

class OuterOne {
    var name = "OuterOne"

    class NestedOne {
        fun call() {
            println("call nested one")
        }
    }

    inner class InnerOne {
        val name = "InnerOne"

        fun callInnerOne() {
            println("call $name")
        }

        fun callOuterOne() {
            println("call ${this@OuterOne.name}")
        }
    }
}
