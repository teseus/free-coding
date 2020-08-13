package com.teseus.inaction.ch7.step1

import java.beans.PropertyChangeSupport


class Person(val name: String, _age: Int, _salary: Int){

    val propertyChangeSupport: PropertyChangeSupport = PropertyChangeSupport(this)

    var age: Int = _age
    set(newValue){
        val oldValue = field
        val field = newValue
        println("property changed name : age, oldvalue: $oldValue, newValue: $field")
    }
}

fun main() {
    val p = Person("Karl", 30, 1000)

    p.age = 31
}
