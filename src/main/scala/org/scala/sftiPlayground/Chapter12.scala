package org.scala.sftiPlayground

// scalastyle:off regex.println
import java.awt.event.ActionEvent

import javax.swing.JButton

object Chapter12 extends App {
  // function literals in scala
  // Here map is a higher order function
  // and n => n * 2 is a function literal
  Array(1,2,3,4,5).map(n => n * 2)

  //The below is not a function literal
  // its a variable holding the function
  // 5 is an integer literal while
  // val num = 5 is a variable holding an integer value
  val doubler = (n: Int) => n * 2

  // In case of java SAM(Single Abstract Method) interfaces
  // we can use only function literals
  val button = new JButton("Submit")

  //We can only pass function literal to the java methods expecting a
  // an object implementing the SAM interface
  button.addActionListener((e:ActionEvent) => println(e.paramString()))

  //=======================================================================
  //*  Currying - converting a method with 2 arguments in to a method with single
  // argument and returning another function that takes the second argument
  // Multiple parameter list is a syntactic sugar to achieve currying
  def sum(x: Int)(y: Int): Int = x + y

  // I can create intermediate methods using currying
  val add10: Int => Int = sum(10)(_)
  // adds 10 to every number passed
  println(add10(20))
  println(add10(5))

  // control abstractions
  // Using the call by name notation I can create constructs that look similar to
  // the native language constructs
  // for instance using call by name and currying I can implement a control structure
  // like until

  def until[T](condition: => Boolean)(block: => Unit): Unit ={
    if (!condition) {
      block
      until(condition)(block)
    }
  }

  var counter = 10
  until (counter == 0) {
    println(counter)
    counter -= 1
  }

  // return from inside anonymous function returns the value
  // as the return value of the named function containing the anonymous function
  def indexOf(str: String, ch: Char) : Int = {
    var i = 0
    // return from an anonymous function
    // returns the value from the enclosing named function
    //The control flow is achieved with a special exception that is
    // thrown by the return expression in the anonymous function,
    // passed out of the until function, and caught in the indexOf function
    until (i == str.length) {
      if (str(i) == ch) return i
      i += 1
    }
    return -1
  }

  println(indexOf("Hello", 'H'))
}
