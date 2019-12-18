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
