package com.teseus.inaction.ch4

interface Clickable {
    fun click()
    fun showOff() = println("It's clickabel")
}

interface Focusable {
    fun showOff() = println("it's focusable")
    fun setFocus(b:Boolean) {
        println("${if (b) "focused" else "Offed"}")
    }
}
class Button : Clickable, Focusable {
    override fun click() = println("button click")
    override fun showOff() { //unless you def   ine it, exception occurs
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

fun main() {
    val button = Button()
    button.click()
    button.showOff()
    button.setFocus(true)
    button.setFocus(false)
}
