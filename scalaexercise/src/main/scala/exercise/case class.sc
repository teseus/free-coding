case class Person(name:String, age:Int)

var p = Person("철수", 15)

p.name
// p.name = "B" impossible.

var p1 = Person("A", 15)
var p2 = Person("A", 15)
var p3 = Person("B", 15)

println(p1 == p2)
println(p1 == p3)

println(p1.toString)

println(p1.hashCode())