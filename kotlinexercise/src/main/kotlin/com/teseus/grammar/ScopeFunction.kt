package com.teseus.grammar

fun main() {
    val book = Book("기사단장", 10000).apply{
        discount(2000)
    }

    println(book.toString1())

    val resultRun = book.run {
        name + "," + price + "in run"
    }

    println(resultRun)

    testForWithLet()
}

fun testForWithLet() {
    val name = "정글만리"

    val a = Book("기사단장", 10000).apply{
        discount(2000)
    }

    val p1 = a.run {
        price
    }
    println("price1 = $p1 in run")

    val p2 = a.let{
        it.price
    }

    println("price2 = $p2 in let")

    val b = a.apply {
        discount(900)
    }

    println("${b.name}, ${b.price} with apply")

    val c = a.also {
        it.discount(800)
    }

    println("${c.name}, ${c.price} with also")

}

class Book(val name:String, var price:Long){
    fun discount(amount : Long){
        price -= amount
    }

    fun toString1() = println("name:$name, price:$price")
}