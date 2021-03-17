package org.scala.rockthejvm.advanced.advfp

// scalastyle:off regex.println

import scala.util.control.Exception
import scala.io.Source

/**
 *
 */
object PartialFunctions extends App {
  /**
   * Lecture notes
   */
  // partial functions work with a subset of values from a larger domain
  // a partial function that works only with even numbers
  // partial functions can only accept a single input parameter
  val evenPF: PartialFunction[Int, String] = {
    case x: Int if (x % 2 == 0) => "even"
  }

  // For any odd number passed to partial function
  println(evenPF(2))
  println(evenPF.isDefinedAt(3))

  // lifts partial function to a complete function
  val convertedFunction: Int => Option[String] = evenPF.lift
  println(convertedFunction(2), convertedFunction(3))

  // partial functions are subclasses of normal function Function1[-A, +B]
  // so we can pass PF to any higher order function
  val oddPF: PartialFunction[Int, String] = {
    case x: Int if (x % 2 != 0) => "odd"
  }

  // map is a  higher order function to which we have passed a partial function
  // partial functions can be chained using orElse, andThen
  println((1 to 10).map(evenPF.orElse(oddPF)))

  //andThen chaining
  println(evenPF.andThen({
    case "even" => "E"
  })(10))

  /**
   * Exercises
   */

  // 1 - construct a PF instance using anonymous class
  // We need to override apply and isDefinedAt to instantiate
  // Partial function anonymously
  val pf = new PartialFunction[Int, Int] {
    override def apply(n: Int): Int = {
      n match {
        case 1 => 100
        case 10 => 1000
      }
    }

    override def isDefinedAt(x: Int): Boolean = {
      // I could declare something called domain and checked x against it
      // Instead I chose to call the apply, though its inefficient
      Exception.allCatch.either {
        this.apply(x)
      } isRight
    }
  }

  println(pf(1))
  println(pf.isDefinedAt(2))

  // 2 - dumb chatbot as a partial function
  val chatbot: PartialFunction[String, String] = {
    case "Hello" => "Hello"
    case "Bye" => "Bye"
    case "How are you?" => "I am good. How are you?"
  }

  println("Chatbot is started. Start typing to interact with the bot!")
  Source.stdin
    .getLines()
    .map(chatbot.orElse({
      case _: String => "Sorry I don't have a response for your query."
    }))
    .foreach(resp => println(s"Bot: $resp"))

}
