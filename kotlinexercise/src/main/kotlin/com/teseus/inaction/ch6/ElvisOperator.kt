package com.teseus.inaction.ch6

fun main() {
    with(
            Person("teseus", "teseus@wemakeprice.com",
                    Company("wmp",
                            Address("teheranro", 135030, "seoul", "Korea")))
    ) {
        showAddress(this)
    }

    with(Person("someone", null, null)) {
        showAddress(this)
    }
}

fun showAddress(person: Person) {
    println(person.company?.address?.streetAddress ?: throw IllegalArgumentException("No Address"))
    with(person.company?.address) {
        println("$zipCode, $city, $country")
    }
}
