//Curling

def modN(n:Int, x:Int) = (x%n) == 0
modN(10, 30)
modN(3, 10)

def modNCur(n:Int)(x:Int) = (x%n) == 0

def modOne:Int => Boolean = modNCur(3)
def modTwo = modNCur(3) _

println(modOne(30))
println(modOne(10))

def modNCur2(n:Int)(x:Int) = modN(n, x)

def modOne2:Int => Boolean = modNCur2(3)
def modTwo2 = modNCur2(3) _

println(modOne2(30))
println(modOne2(10))

//========== exercise ===========

def myCur(x:Int)(y:Int):Boolean = x%y == 0

def myCurl1:Int => Boolean = myCur(10)
def myCurl2 = myCur(10) _

println(myCurl1(2))
println(myCurl1(3))
println(myCurl2(2))
println(myCurl2(3))

object CurryTest extends App {
  // 재귀를 이용하여 xs를 처음부터 끝까지 순환하는 filter
  // 리스트를 head, tail로 구분하여 시작부터 끝까지 순환
  def filter(xs: List[Int], p: Int => Boolean): List[Int] =
    if (xs.isEmpty) xs
    else if (p(xs.head)) xs.head :: filter(xs.tail, p)  // :: 는 리스트를 연결하는 함수
    else filter(xs.tail, p)

  def modN(n: Int)(x: Int) = ((x % n) == 0)
  val nums = List(1, 2, 3, 4, 5, 6, 7, 8)

  println(filter(nums, modN(2)))    // List(2, 4, 6, 8)
  println(filter(nums, modN(3)))    // List(3, 6)
}

println(CurryTest.main(Array()))