package com.teseus.inaction.ch6

data class Address(val streetAddress: String, val zipCode: Int,
                   val city: String, val country: String)

data class Company(val name: String, val address: Address?)

data class Person(val name:String, val email:String ?, val company: Company?)