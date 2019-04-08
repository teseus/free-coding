object payrollCaculator{
  def calc(workType:String, workedHours:Int) ={
    var rate = 10.50
    if(workType=="s")
      if(workedHours<40){
        workedHours * rate
      } else {
        workedHours * rate * 1.5
      }
    else
      println("already paid")
  }

  calc("s", 40)
  calc("s", 39)
  calc("h", 39)


}