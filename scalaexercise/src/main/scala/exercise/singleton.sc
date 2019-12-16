//스칼라에서 obejct 선언자로 싱글톤 객체를 생성합니다. 싱글톤 객체의 메서드는 전역적으로 접근하고 참조할 수 있습니다.
//싱글톤 객체는 직접적으로 접근해서 사용할 수도 있고, import 를 선언하여 이용할 수도 있습니다.

object Bread {
  val name:String = "바께뜨"
  def cooking():String = s"$name 굽는중"
}

println(Bread.name)
println(Bread.cooking())

import Bread.cooking
cooking()
/*
싱글톤 객체와 클래스가 같은 이름을 사용하면 컴패니언(Companions)이라고 합니다.
컴패니언은 정적 메소드의 보관 장소를 제공합니다. 자바의 static을 이용한 정적 데이터는 스칼라에서는 컴패니언을 이용하여 처리하는 것을 추천합니다. 팩토리 메소드 같은 정적 메소드는 컴패니언을 이용하여 작성하고, 일반적인 데이터는 클래스를 이용하여 정적 데이터와 일반 데이터를 분리하여 관리할 수 있습니다.
*/

class Dog
object Dog{
  def bark() = println("멍멍")
}

Dog.bark()

//컴패니언을 이용한 팩토리 예제

class Email(val id:String, val domain:String)

object Email{
  def fromString(text:String):Option[Email] = {
    text.split("@") match {
      case Array(a,b) => Some(new Email(a,b))
      case _ => None
    }
  }
}

val myEmail = Email.fromString("teseus@gmail.com")

myEmail match {
  case Some(email) => println(s"An email is made " +
    s"with id = ${email.id} and " +
    s"domain = ${email.domain}")
  case None => println("there is nothing made")
}

object S01_HelloWorldObject {
  def main(args: Array[String]): Unit = {
    println("Hello World main")
  }
}

object S02_HelloWorld extends App{
  println("Hello World App")
}