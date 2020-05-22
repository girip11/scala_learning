package org.scala.fp

object Functions extends App {
  // 1. string concatenation
  def stringAppender: (String, String) => String = new ((String, String) => String) {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  println(stringAppender("Hello", "World"))

  //Function that accepts int and returns another
  //function that accepts int and returns another int
  //Thefunction returned is called closure.
  val process: (Int => (Int => Int)) = new (Int => (Int => Int)) {
    override def apply(x: Int): Int => Int = new (Int => Int) {
      override def apply(y: Int): Int = x + y
    }
  }

  val tripler = process(3)
//  triples the passed value
  println(tripler(9))
}
