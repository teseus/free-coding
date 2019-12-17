for(i<-0 to 3)
  println(i)

for(i<-0 until 3)
  print(i)

val alphas = Array("a","b","c","d","e","f")

for(alpha<-alphas)
  println(alpha)

for(i<-0 until alphas.length)
  println(i, alphas(i))

for((value, index) <- alphas.zipWithIndex)
  println(value, index)

val map = Map("k1"->"a", "k2"->"a", "k3"->"c", "k4" -> "d")

for((k,v)<-map)
  println(k,v)

for(i<-0 to 5; j<-1 to 4)
  println(i,j)

for(i<-0 to 2; if i != 1)
  println(i)

for(i<-0 to 2;j<- 0 to 2; if i != 1)
  println(i,j)

for(i<-0 to 2;j<-0 to 2;if i!=1; if j!=1)
  println(i,j)

def five(n:Int) = {
  for(i<- 0 to n; if i%5 == 0)
    yield i
}

for(i<-five(100))
  println(i)

def checkSum(num: Int, sum: Int) =
  for (
    start <- 0 until num;
    inner <- start until num if start + inner == sum
  ) yield (start, inner)

checkSum(20, 32) foreach {
  case (i, j) =>
    println(s"($i, $j)")
}

var i = 0

do{
  println(i)
  i += 1
} while (i < 3)

i = 0
while(i < 3){
  i += 1
  println(i)
}
