import scala.collection.mutable

trait TestStack[T]{
  def pop():T
  def push(value:T)
}

class StackSample[T] extends TestStack[T] {
  val stack = new mutable.Stack[T]

  override def pop(): T = {
    stack.pop()
  }

  override def push(value: T): Unit = {
    stack.push(value)
  }

}

val s = new StackSample[String]

s.push("1")
s.push("2")
s.push("3")

println(s.pop())
println(s.pop())
println(s.pop())