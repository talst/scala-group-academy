import scala.collection.GenSeq

val sleepTimes = List(500, 1000, 1500, 2000)

def sleep(time: Int) = Thread.sleep(time)

def recordSleepTime(list: GenSeq[Int]) = {
  val t = System.currentTimeMillis()
  list foreach sleep
  System.currentTimeMillis - t
}

recordSleepTime(sleepTimes)
recordSleepTime(sleepTimes.par)
