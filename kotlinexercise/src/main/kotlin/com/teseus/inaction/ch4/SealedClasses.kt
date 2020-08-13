package com.teseus.inaction.ch4

interface Expr
class Num(val value : Int) : Expr
class Sum(val left:Expr, val right:Expr) : Expr
class EXpression(val msg: String) : Expr

fun eval(e:Expr): Int =
        when(e) {
            is Num -> e.value
            is Sum -> eval(e.left) + eval(e.right)
            else ->
                throw IllegalArgumentException("Unknown Exception")
        }

sealed class Expr1{
    class Num1(val value: Int) : Expr1()
    class Sum1(val left: Expr1, val right: Expr1) : Expr1()
}

//class Expression1(val msg: String) : Expr1()

fun eval1(e:Expr1) : Int =
        when(e){
            is Expr1.Num1 -> e.value
            is Expr1.Sum1 -> eval1(e.left) + eval1(e.right)
        }

fun main() {

    val sum1 = Expr1.Sum1(Expr1.Num1(10), Expr1.Num1(30))

    println(eval1(sum1))

    val sum = Sum(Num(1), Num(3))

    println(eval(sum))
    println(eval(EXpression("가")))
}