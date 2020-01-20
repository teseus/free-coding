package com.teseus.grammer

fun main(){
    EventPrinter().start()
    EventPrinter2().start()
}

class EventPrinter2 {
    fun start() {
        Counter(object:EventListner{
            override fun onEvent(cnt: Int) {
                print("Ev2-$cnt, ")
            }
        }).count()
        println()
    }
}

class EventPrinter : EventListner{
    fun start(){
        Counter(this).count()
        println()
    }

    override fun onEvent(cnt: Int) {
        print("Ev1-$cnt, ")
    }
}

class Counter(var eventListener: EventListner) {
    fun count(){
        for(i in 1..100) {
            if (i % 5 == 0) {
                eventListener.onEvent(i)
            }
        }
    }
}

interface EventListner {
    fun onEvent(cnt:Int)
}
