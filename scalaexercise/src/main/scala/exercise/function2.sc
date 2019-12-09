//=== just define Lamda function ==

def exec(f:(Int, Int) => Int, x:Int, y:Int) =  f(x,y)

exec((a:Int, b:Int) => a+b, 7, 3)

exec((a,b)=>a+b, 7, 3)

exec(_+_, 7, 3)

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


