package com.teseus.inaction.ch3

open class Base{
    open fun v(){
        println("open fun v")
    }

    fun nv(){
        println("close fun nv")
    }
}

class DerivedBase : Base() {
    override fun v() {
        println("derived v")
    }

    /* overriding nv is impossible
   override fun nv(){
        println("derived nv")
    }*/
}

fun main(){
    Base().v()
    Base().nv()

    DerivedBase().v()
    DerivedBase().nv()
}


