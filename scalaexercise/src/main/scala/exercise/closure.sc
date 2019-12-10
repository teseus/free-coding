def divide(n:Int) = (x:Int) => {
  x/n
}

def divideFive = divide(5)
println(divideFive(10))

var factor = 10

def multiplier = (x:Integer) => {
  x * factor
}

println(multiplier(5))

factor = 100

println(multiplier(5))
