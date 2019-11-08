package demo

object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello, World")

    var firstName:String = "teseus"
    val age:Int = 21
    println(firstName + ", is " + age + " years old")
    var sum = 10 + 25
    def function1:Unit = {
      println("This is function1")
    }
    function1
  }
}
