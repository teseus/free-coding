val random1 = Math.random()
var random2 = Math.random()
def random3 = Math.random()

// random1, random2는 호출할 때마다 같은 값 반환
// 선언 시점에 값이 확정됨
random1
random1

random2
random2

random3
random3


def run() {
  def middle() {
    println("middle")
  }

  println("start")
  middle()
  println("end")
}

run()