package org.scala.scalaImpatient.chapter8

//Define an abstract class Shape with an abstract method centerPoint and subclasses
//Rectangle and Circle. Provide appropriate constructors for the subclasses and
//override the centerPoint method in each subclass.

object Exercise6 extends App {

  abstract class Shape {
    def centerPoint: (Double, Double)
  }

  class Rectangle(val x1: Double,
                  val y1: Double,
                  val x2: Double,
                  val y2: Double) extends Shape {
    override def centerPoint: (Double, Double) = ((x1 + x2)/2, (y1 +y2)/2)
  }

  // I can accept coordinates of endpoints of a diameter
  // to compute the center of the circle.
  class Circle(val radius: Double) extends Shape {
    override def centerPoint: (Double, Double) = ???
  }
}
