package com.teseus.grammar

fun main() {
    val cola1 = Product("코카콜라", 1000)
    val cola2 = Product("코카콜라", 1000)
    val cola3 = cola1
    val cola4 = Product("펩시콜라", 900)

    println(cola1 == cola2)
    println(cola1 === cola2)

    println(cola1 == cola3)
    println(cola1 === cola3)

    println(cola1 == cola4)
    println(cola1 === cola4)

}

class Product(val name:String, val price:Long){
    override fun equals(other: Any?): Boolean {
        if(other is Product){
            return other.name == this.name && other.price == this.price
        }
        return false
    }
}