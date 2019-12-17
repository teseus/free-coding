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