package com.teseus.inaction.ch6

fun sendToEmail(email: String){
    println("sending Email to $email")
}

fun getEmailAddres(person: Person): String? = person?.email

fun main() {
    with(
            Person("teseus", "teseus@wemakeprice.com",
            Company("wmp",
                    Address("teheranro", 135030, "seoul", "Korea")))
    ){
        //sendToEmail(getEmailAddres(this)) impossible
        getEmailAddres(this)?.let {
            sendToEmail(it)
        }
    }

    with(Person("someone", null, null)) {
        getEmailAddres(this)?.let {
            sendToEmail(it)
        }
    }
}
