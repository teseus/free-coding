package com.teseus.grammar

import reactor.core.publisher.Flux

fun main() {
    val fruits = listOf("apple", "banana", "peach")

    for(fruit in fruits){
        println(fruit)
    }

    val numbers = mutableListOf(1, 2, 3)
    println(numbers)
    numbers.add(4)
    println(numbers)
    numbers.add(2, 8)
    println(numbers)
}