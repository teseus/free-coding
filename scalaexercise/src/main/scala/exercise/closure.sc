def divide(n:Int) = (x:Int) => {
  x/n
}

def triple(x:Int) = (y:Int) => (z:Int) => {
  x + y + z
}

def triple_one = triple(1)

def triple_second = triple_one(2)

println(triple_second(3))

def divideFive = divide(5)
println(divideFive(10))

var factor = 10

def multiplier = (x:Integer) => {
  x * factor
}

println(multiplier(5))

factor = 100

println(multiplier(5))
