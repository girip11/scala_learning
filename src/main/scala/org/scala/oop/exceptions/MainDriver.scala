package org.scala.oop.exceptions

object MainDriver extends App {

    val pocketCalc = new PocketCalculator
    println(pocketCalc.add(100, 100))
    println(pocketCalc.subtract(100, -1000))
    println(pocketCalc.multiply(100, 10))
    println(pocketCalc.divide(100, 1))

//      println(pocketCalc.add(Int.MaxValue, 100))
  //    println(pocketCalc.multiply(Int.MinValue, 3))
//    println(pocketCalc.divide(100, 0))

}
