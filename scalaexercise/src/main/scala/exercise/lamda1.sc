//=== just define Lamda function ==

def myFunc(f:(Int,Int) => Int, x:Int, y:Int) = f(x,y)

myFunc((a,b) => a*b, 2, 10)
myFunc((a,b) => a+b, 2, 10)

myFunc(_+_, 2, 10)

def add(a:Int, b:Int) = a+b
def sub(a:Int, b:Int) = a-b

myFunc(add, 2, 10)
myFunc(sub, 2, 10)



