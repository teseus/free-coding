package com.teseus.inaction.ch3

open class Parent1 {
    open fun v(){
        println("A - v")
    }
}

interface Parent2 {
    fun v(){
        println("B - v")
    }
}

class Child : Parent1(), Parent2 {
    override fun v() {
        super<Parent1>.v()
        super<Parent2>.v()
    }
}

open class View(){
    open fun click() = println("view click")
}

class Button : View() {
    override fun click() = println("Button click")
}

fun main() {
    Child().v()

    val view: View = Button()
    view.click()

    fun View.showOff() = println("I'm View")
    fun Button.showOff() = println("I'm Button")

    val v: View = Button()
    v.showOff()
}