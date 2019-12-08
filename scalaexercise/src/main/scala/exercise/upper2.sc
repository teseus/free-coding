object Upper{
  def upper(strings : String*) = strings.map(_.toUpperCase())
}

println(Upper.upper("Hellow", "Teseus2"))