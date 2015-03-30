implicit class IntPredicates(i: Int) {
  def isEven = i % 2 == 0

  def isOdd = !isEven
}

4.isEven
4.isOdd

class improvedString(x: String) {
  def isEmptyOrNull = x == null || x.isEmpty
}

implicit def stringToImp(x: String): improvedString = new improvedString(x)
val x: String = null
x.isEmptyOrNull
"".isEmptyOrNull
