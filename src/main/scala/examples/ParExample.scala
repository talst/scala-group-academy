package examples

import scala.collection.GenSeq

object ParExample extends App {
  val urls = List("http://scala-lang.org",  "https://github.com/scala/scala", "http://www.sap.com", "http://www.yahoo.com")

  val numberOfIter = 5

  def readUrl(url: String) = {
    val t = System.currentTimeMillis()
    scala.io.Source.fromURL(url).getLines mkString "\n"
    System.currentTimeMillis - t
  }

  def readUrls(urls: GenSeq[String]) = urls map readUrl

  def runTest(urls: GenSeq[String]) = {
    val times = for (i <- 1 to numberOfIter) yield {
      val t = System.currentTimeMillis()
      println("test " + i + ": " + (readUrls(urls) mkString ", "))
      System.currentTimeMillis - t
    }
    println("total: " + (times mkString ", ") + "\n" +
      "avg: " + times.sum / 5 + "\n")
  }

  runTest(urls)
  runTest(urls.par)
}
