val list = (1 to 3)
list.map(_+1)

val names = List("david", "james","kevin")

names.map(_.toUpperCase)

list.reduce(_+_)
list.reduceLeft(_+_)
list.reduceRight(_+_)
list.sum

list.reduce(_-_)
list.reduceLeft(_-_)
list.reduceRight(_-_)

list.fold(10)(_+_)

val pairs = List(("A",1),("B",1),("C",1),("A",2),("B",2),("C",2),("A",3),("B",3))
pairs.groupBy(_._1).foreach({case (k,v) => println(s"key: $k, value: $v")})

//f p find

val nums = (1 to 10)
nums.filter(_>5)

nums.partition(_%3 == 0)

nums.find(_==5)

var nums2 = List(1, 2, 3, -1, 4, 5, 6)
nums2.takeWhile(_>0)
nums2 = List(-1, 2, 3, 1, 4, 5, 6)
nums2.takeWhile(_>0)
nums2 = List(1, 2, 3, 1, 4, 5, -6)
nums2.takeWhile(_>0)


var nums3 = List(3, 1, 2, 3, 4, 5, 6, 1)
nums3.dropWhile(_<3)
nums3 = List( 1, 2, 3, 4, 5, 6, 1)
nums3.dropWhile(_<3)
nums3 = List(1, 2, 3, 1, 4, 5, 6, 1)
nums3.dropWhile(_<3)

for(item<-List(1,2,3).zip(List(3,2,1)))
  println(item)

for(item<-List(1,2,3).zip(List(4,3,2,1))) {
  println(item)
}

var maps = Map("A" -> 1, "B" -> 2, "C" -> 3, "D" -> 4, "E" -> 5)
maps.mapValues(x => x * x).foreach {
  case (k, v) => println(s"key:$k, value:$v")
}

maps = Map("A" -> 1, "B" -> 2, "C" -> 3, "D" -> 4, "E" -> 5)
maps.mapValues(x=>x*x).foreach(e=>e match {
  case (k,v) => println(s"key:$k, value:$v")
})

var maps2 = Map("A"->List(1,2,3),"B"->List(2,3,4),"C"->List(3,4,5))
maps2.mapValues(_.sum).foreach{ case (k,v)=> println(s"key:$k, value:$v")}

var list1 = List(4,6,1,6,0)
var l_sort = list1.sorted
var r_sort = list1.sorted(Ordering.Int.reverse)

var sList = List("aa", "bb", "cc")
sList.sortBy(_.charAt(0))

val l_sortwith = sList.sortWith(_ <= _)
val r_sortWith = sList.sortWith(_ >= _)

case class Person(index:Int,correct:Int)

val persons = Array(Person(1, 3),Person(2, 4), Person(3, 4))

persons.sortWith((l:Person, r:Person) => {
  if(l.correct == r.correct)
    l.index >= r.index

  l.correct > r.correct
})