package org.scala.sftiPlayground

//scalastyle:off regex.println

object Chapter14 extends App {
  //case classes
  //1. constructor parameters automatically become val fields
  //2. default implementations of apply, unapply(extractor),
  // equals, hashCode, copy and toString
  //3. By default serializable

  // we can also have case objects similar to singleton object

  // Applications
  //1. Pattern matching
  //2. Implement enumerations with shelsealed bases classes
  //3. More suitable for implementing value classes that holds some value and
  // are immutable

  // pattern matching
  //1. Case classes that return a 2-tuple/pair on unapply method can be used in
  // the case clauses in infix notation
  val simpleList = List(1,2,3)
  def iterateList(list: List[Int]) : Unit = {
    list match {
      case Nil => ()
      // here :: is a case class
      case head :: tail => {
        println(head)
        iterateList(tail)
      }
    }
  }
  iterateList(simpleList)

  //2. Case classes are very useful in pattern matching of nested structures
  simpleList match {
    // Here the second element is taken from nested structure
    // To demonstrate nested structures, I have used the class notation and not infix notation
    case ::(first, ::(second, List(_))) => println(first, second)
    case _ => ()
  }

  //3. Enumerations using sealed class or trait
  sealed trait TrafficLight
  case object Red extends TrafficLight
  case object Yellow extends TrafficLight
  case object Green extends TrafficLight

  val trafficLight: TrafficLight = Red
  println(trafficLight match {
    case Red => "Stop"
    case Yellow => "Ready"
    case Green => "Go"
  })


  // partial functions
  // a set of case clauses enclosed in {}. It may not contain a matching
  // for all inputs

  // Where it the partial functions used in scala?
  // In the collect method on collections, partial functions are used.
  // in try catch, the catch accepts a partial function
  println(Seq(1,2,3,4,5).collect {
    case n if n % 2 == 0 => n
  })

  //==========================================================
  // PartialFunction and its methods
  // I can also store the partial function in a variable and pass it to
  // other methods accepting partial functions
  val partialFunction:PartialFunction[Int, Int] = {
    case n if n % 2 == 0 => n
  }
  println(Seq(1,2,3,4,5).collect(partialFunction))

  // "apply()" on the PartialFunction object returns the output
  // raises scala.MatchError when no case clauses match the input(for an odd number)
  println(partialFunction(2))

  // PartialFunction also contains a method called isDefinedAt
  // to check if a case clause can handle the input
  // Since 5 is not even this method returns false
  println(partialFunction.isDefinedAt(5))

  //===================================================
  // Sequences, maps and partial functions
  // Sequence can be viewed as a Map[Int, Value] where the key is the index
  // Map[K,V] is a generic Map where Key can be of any type
  // Seq[T] becomes a PartialFunction[Int, T] while
  // Map[K,V] becomes a PartialFunction[K,V]

  val lookupTable = Map(
    'a' -> "A",
    'e' -> "E",
    'i' -> "I",
    'o' -> "O",
    'u' -> "U"
  )

  // each character will be passed to the map
  // and we will get the corresponding value in the map
  print("hello world".collect(lookupTable))

  //======================================================================
  // PartialFunction to function conversion using lift method
  // Conversely, unlift method on a function that returns Option[T] is converted
  // to partial function
  val func: (Int => Option[Int]) = partialFunction.lift
  println(func(5))

  def myLiftImp[A,B](pf: PartialFunction[A,B]): (A => Option[B]) = {
    (x: A) => try {
      Some(pf(x))
    } catch {
      case _: MatchError => None
    }
  }

  println((myLiftImp(partialFunction)(5)))
  println((myLiftImp(partialFunction)(2)))


  // example snippet
  val varPattern = """\{([0-9]+)\}""".r
  val message = "At {1}, there was {2} on {0}"
  val vars = Map("{0}" -> "planet 7", "{1}" -> "12:30 pm",
    "{2}" -> "a disturbance of the force")
  val result = varPattern.replaceSomeIn(message, m => vars.get(m.matched))
  // I can also use the lift method on Map
  val result2 = varPattern.replaceSomeIn(message, m => vars.lift(m.matched))
  println(result == result2)

}

