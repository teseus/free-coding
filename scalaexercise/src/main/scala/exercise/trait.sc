/*트레잇(trait)은 자바의 인터페이스와 유사합니다. 메소드를 정의만 해놓을 수도 있고, 기본 구현을 할 수도 있습니다. 추상 클래스와 달리 생성자 파라미터는 가질 수 없습니다.

트레잇에서는 가변 변수, 불변 변수 모두 선언 가능합니다. 트레잇을 구현하는 클래스에서 가변 변수는 수정이 가능하지만, 불변 변수는 수정할 수 없습니다.

트레잇의 기본 메소드는 상속되고, override 키워드를 이용하여 메소드를 재정의 할 수도 있습니다.

트레잇은 extends로 상속하고 여러개의 트레잇을 with 키워드로 동시에 구현할 수 있습니다.

상속하여 구현할 수 있기 때문에 추상클래스와 유사하지만 멤버변수를 가질 수는 없습니다.

또한 추상클래스는 하나만 상속할 수 있지만, 트레잇은 여러개을 상속 할 수 있습니다.

생성자 멤버변수가 필요하면 추상클래스를 이용하는 것이 좋고, 멤버 변수가 필요 없다면 트레잇을 이용하는 것이 좋습니다.
 */

trait Machine {
  val serialNumber:Int = 1
  def work(message:String)
}

trait KrMachine {
  var countryCode:String = "korea"
  def doing(message:String) = println(s"I'am doing $message")
}

class Computer(location:String) extends KrMachine with Machine {
  countryCode = "us"

  override def work(message: String): Unit = println(s"I am working $message in $countryCode")
}

val computer = new Computer("노트북")
computer.work("슈퍼소닉")

class Car(location:String) extends Machine with KrMachine {
  override def work(message: String): Unit = println(s"the car is working with $message in $location")

  override def doing(message: String): Unit = println(s"overrided doing $message countryCode $countryCode")
}

val car = new Car("현대")
car.doing("운전중")
car.work("세차중")

println(car.countryCode)