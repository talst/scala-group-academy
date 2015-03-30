def doSomething(x: Int) = {
  Thread.sleep(1000)
  x + 10
}
val t = System.currentTimeMillis()
((1 to 5).par map doSomething).sum
System.currentTimeMillis - t
