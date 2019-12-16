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
