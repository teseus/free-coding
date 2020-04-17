package com.teseus.inaction.ch3

fun main() {
    println("abc.efg-123".split(".", "-"))
    println("abc.efg-123".split("\\.|-".toRegex()))

    parsePathWithRegex("/api/log/model/wlog.doc")
    parsePath("/api/log/model/wlog.doc")
    multiLine()
}

fun parsePathWithRegex(path:String){
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val result = regex.matchEntire(path)

    if(result!=null) {
        val (s, s1, s2) = result.destructured

        println("Dir: $s, name: $s1, ext: $s2")
    }
}

fun parsePath(path:String){
    val directory = path.substringBeforeLast("/")
    val filenameAll = path.substringAfterLast("/")

    val fileName = filenameAll.substringBeforeLast(".")
    val extension = filenameAll.substringAfterLast(".")

    println("Dir: $directory, name: $fileName, ext: $extension")
}

fun multiLine() {
    val kotlinLogo = """| //
                   .|//
                   .|/ \"""
    println(kotlinLogo.trimMargin("."))
}

