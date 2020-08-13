package com.teseus.inaction.ch7.step2

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport

class Person(val name: String, _age: Int, _salary: Int){

    val propertyChangeSupport: PropertyChangeSupport = PropertyChangeSupport(this)

    var age: Int = _age
    set(newValue){
        val oldValue = field
        val field = newValue
        propertyChangeSupport.firePropertyChange("age", oldValue, newValue)
    }
}

fun main() {
    val p = Person("Karl", 30, 1000)
    p.propertyChangeSupport.addPropertyChangeListener(PropertyChangeListener { event ->
        println("property changed name : ${event.propertyName}, oldvalue: ${event.oldValue}, newValue: ${event.newValue}")
    })

    p.age = 31
}
