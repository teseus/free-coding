object higherOrderFn{
  val doubleFn = (in:Int) => {in * 2}
  def calcFn(arg:Int, fn:Int=>Int) = fn(arg)

  calcFn(3, (in:Int) => in)
  calcFn(3, doubleFn)
}