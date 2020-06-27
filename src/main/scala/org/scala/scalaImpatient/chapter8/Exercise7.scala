package org.scala.scalaImpatient.chapter8

// Provide a class Square that extends java.awt.Rectangle and has three constructors:
//one that constructs a square with a given corner point and width, one
//that constructs a square with corner (0, 0) and a given width, and one that
//constructs a square with corner (0, 0) and width 0.

object Exercise7 extends App {

  import java.awt.Rectangle //scalastyle:ignore

  class Square(val cornerPoint:(Double, Double), width: Int) extends Rectangle {
    def this(width: Int) {
      this((0,0), width)
    }

    def this() {
      this(0)
    }
  }
}
