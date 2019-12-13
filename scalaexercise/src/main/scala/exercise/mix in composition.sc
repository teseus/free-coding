/*
믹스인 컴포지션은 클래스와 트레잇을 상속할 때 서로 다른 부모의 변수, 메소드 를 섞어서 새로운 정의를 만드는 것입니다.

추상 클래스 A는 message 변수를 가지고 있습니다. 클래스 B는 추상 클래스 A를 상속 하면서 변수 message를 초기화합니다. 트레잇 C는 추상 클래스 A를 상속하면서 loudMessage 함수를 선언합니다.

클래스 D는 클래스 B와 트레잇 C를 믹스인하여 클래스 B의 message 를 이용하는 loudMessage 함수를 생성할 수 있습니다.
 */

abstract class A{
  val msg:String
}

class B extends A {
  val msg = "B instance"
}

trait C extends A {
  def loudMessage = msg.toUpperCase()
}

class D extends B with C

val d = new D

println(d.msg)
println(d.loudMessage)

