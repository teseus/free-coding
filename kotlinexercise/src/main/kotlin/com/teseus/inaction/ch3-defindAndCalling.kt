package strings

fun main() {
    val abc = listOf("A", "B", "C")
    val numbers = listOf(1,2,3,4)
    println(joinToString(abc, "(", ',', ")"))
    println(joinToString(numbers, "(", ',', ")"))
    println(joinToString(numbers, postfix = ")", prefix = "(", separator = ',' ))
    println(joinString(numbers))

    println("test last cha$".lastChar())

    println(numbers.joinToString(" "))
}

fun <T> joinToString(collection: Collection<T>, prefix: String, separator: Char, postfix: String): String {
    val result = StringBuilder(prefix)

    for((idx,value) in collection.withIndex()){
        if(idx != 0) result.append(separator)
        result.append(value)
    }

    result.append(postfix)
    return result.toString()
}

fun <U> joinString(collection: Collection<U>, prefix1: String = "(", separator1: Char = ',', postfix1: String = ")"):String{
    val joinToString:String = joinToString(collection, prefix1, separator1, postfix1)
    return joinToString
}

fun String.lastChar(): Char = this.get(this.lastIndex)

fun <T> Collection<T>.joinToString(saparater:String = ",", prefix:String = "", postfix: String = ""):String {
    val sb:StringBuilder = StringBuilder()

    sb.append(prefix)

    for((idx, v) in this.withIndex()) {
        if (idx > 0) sb.append(saparater)
        sb.append(v)
    }

    sb.append(postfix)

    return sb.toString()
}