package com.teseus.inaction.ch6

fun strLen(str:String?) = str?.length?:0

fun main(){
    println( strLen(null) )
    println( strLen("Elvis is a legend") )
}