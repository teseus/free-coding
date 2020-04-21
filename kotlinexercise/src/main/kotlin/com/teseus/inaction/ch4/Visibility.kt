package com.teseus.inaction.ch4

internal class TalkativeButton : Focusable{
    private fun yell() = println("yell")
    protected fun whisper() = println("whisper")

    fun speakOut() = println("speakOut")
}

fun TalkativeButton.giveSpeech(){ //internal classes cannot expose any public functions
    yell() //it is private in TalkativeButton

    whisper() //it is protected in TalkativeButton
    speakOut()
}

fun main() {
    TalkativeButton().speakOut()
}