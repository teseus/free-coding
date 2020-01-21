package com.teseus.grammar

fun main() {
    UsingGeneric(A()).callShout()
    UsingGeneric(B()).callShout()
    UsingGeneric(C()).callShout()

    usingFunGeneric(A())
    usingFunGeneric(B())
    usingFunGeneric(C())
}

fun <T : A> usingFunGeneric(t:T){
    t.shout()
}

open class A {
    open fun shout() {
        println("A 가 소리칩니다.")
    }
}

class B : A() {
    override fun shout() {
        println("B 가 소리칩니다.")
    }
}

class C : A() {
    override fun shout() {
        println("C 가 소리칩니다.")
    }
}

class UsingGeneric<T:A>(val shouting:T) {
    fun callShout(){
        shouting.shout()
    }
}
