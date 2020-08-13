package com.teseus.inaction.ch7.step3

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport

open class PropertyChangeAware(){
    protected val propertyChangeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener) =
            propertyChangeSupport.addPropertyChangeListener(listener)

    fun removePropertyChangeListener(listener: PropertyChangeListener) =
            propertyChangeSupport.removePropertyChangeListener(listener)
}

class Person(val name: String, _age: Int, _salary: Int) : PropertyChangeAware(){
    var age: Int = _age
    set(newValue){
        val oldValue = field
        val field = newValue
        propertyChangeSupport.firePropertyChange("age", oldValue, newValue)
    }

    var salary: Int = _salary
        set(newValue){
            val oldValue = field
            val field = newValue
            propertyChangeSupport.firePropertyChange("salary", oldValue, newValue)
        }
}

fun main() {
    val p = Person("Karl", 30, 1000)
    p.addPropertyChangeListener(
            PropertyChangeListener { event ->
                println("property changed name : ${event.propertyName}, oldvalue: ${event.oldValue}, newValue: ${event.newValue}")
            }
    )

    p.age = 31
    p.salary = 1100
}
