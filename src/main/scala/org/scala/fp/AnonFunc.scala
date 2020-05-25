package org.scala.fp

object AnonFunc extends App {

  val adder: (Int => (Int => Int)) = (inc: Int) => (value: Int) => value + inc
  val addThree = adder(3)
  println(addThree(9))
}
