class Person(name:String, age:Int)

val p = new Person("teseus", 35)

println(p)

class A

class Animal(name:String) {
  println(s"${name} 생성")
}

class Dog(var name:String) {
  println(s"${name} 생성")
}

class Cat(val name:String) {
  println(s"${name} 생성")
}

var ani = new Animal("동물")
var dog = new Dog("개")
var cat = new Cat("고양이")

//println(ani.name) it's impossible

println(dog.name)

println(cat.name)

dog.name = "바둑이"

// cat.name = "나비" it's impossible

// default value for member variables.

class Person1(name:String, age:Int)
class Person2(var name:String, var age:Int = 10)
class Person3(val name:String="Ted", val age:Int)

var p1 = new Person1("David", 12)
var p2 = new Person2("Tomphson")
var p3 = new Person3("David", 12)

// name 에 기본값을 입력할 수 없어서 오류 발생
// var p3 = new Person3(12) impossible

class PersonA(name:String, age:Int, var job:String) {
  def greeting()= println(s"${name}님의 나이는 ${age} 입니다.")
  def work() = println(s"${name}님의 직업은 ${job} 입니다.")
  println("It's the constructor for PersonA")
}

class Writer(name:String, age:Int) extends PersonA(name, age, ""){
  override def work(): Unit = println(println(s"${name}님의 직업은 작가입니다."))
}

var a = new PersonA("teseus", 29, "programmer")
a.greeting
a.work
var b = new Writer("stanley", 28)
b.greeting()
b.work()

val per = new PersonA("David", 15, "학생") {
  override def work() = println(s"job is ${job}. ")
}

per.work()

abstract class PersonB(name:String, age:Int){
  def work
  def status(st:String)
  def greeting() = println(s"${name} 님은 ${age}살 입니다.")
}

class Player(name:String, age:Int) extends PersonB(name, age) {
  override def work: Unit = println(s"${name}님은 Player입니다.")

  override def status(st: String): Unit = println(s"${name}님의 상태는 ${st}입니다.")
}

var player = new Player("Tom", 25)
player.greeting
player.work
player.status("좋음")

//this class cannot be extened out of this file.
sealed abstract class Furniture
case class Couch() extends Furniture
case class Chair() extends Furniture

