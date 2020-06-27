package org.scala.scalaImpatient.chapter8

// 5. Design a class Point whose x and y coordinate values can be provided in a
//constructor. Provide a subclass LabeledPoint whose constructor takes a label
//value and x and y coordinates, such as
//new LabeledPoint("Black Thursday", 1929, 230.07)

object Exercise5 extends App {

  class Point(val x: Double, val y: Double) {
    override def toString: String = s"($x, $y)"
  }

  class LabeledPoint(val label: String, x: Double, y: Double) extends Point(x,y)

  val labeledPoint = new LabeledPoint("Black Thursday", 1929, 230.07)
  println(labeledPoint) //scalastyle:ignore



}
