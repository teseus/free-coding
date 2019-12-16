// Array
val array1 = Array(1,2,3)
println(array1(1))
array1(1) = 10
println(array1(1))
val array2 = Array(4,5,6)
val array3 = array1 ++ array2
val array4 = 0 +: array3
val array5 = array3 :+ 100

// List
val list1 = List(10, 20, 30, 40)
val list2 = (1 to 100).toList
val list3 = array5.toList

list1
list1(0)
list1(3)
list1.head
list1.tail

//Set
val set1 = Set(1,1,2)
set1
set1(1)
set1(2)
set1(3)

//tuple 튜블은 불변의 데이터를 저장하는 자료구조 입니다.
// 여러가지 값을 저장할 수 있습니다. 값에 접근할 때는 _1, _2와 같은 형태로 접근합니다.
// 튜플은 패턴매칭에 이용할 수 있습니다.

val tuple = ("tuple.com", 80, true)
tuple._1
tuple._2
tuple._3

def tupleMatch(host:(Int, String)) =
  host match {
    case (port, "teseus.com") => println(s"case 1 teseus.com, $port")
    case (port, url) => println(s"case 2 $host")
  }

tupleMatch(80, "teseus.com")
tupleMatch(80, "test.com")
