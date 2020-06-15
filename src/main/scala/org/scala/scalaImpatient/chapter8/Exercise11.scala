package org.scala.scalaImpatient.chapter8

//11. Define a value class Point that packs integer x and y coordinates into a Long
//(which you should make private)
object Exercise11 extends  App {

  class Point private(private val packed: Long) extends AnyVal {
    def x: Int = (packed >> 32).toInt
    def y: Int = packed.toInt
  }

  object Point {
    def apply(x: Int, y: Int): Point = new Point((x.toLong << 32) | y)
  }

  val pnt = Point(10, 20)
  println(pnt.x, pnt.y)
}
