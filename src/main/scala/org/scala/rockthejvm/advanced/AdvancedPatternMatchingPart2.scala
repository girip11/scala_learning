package org.scala.rockthejvm.advanced

import java.util.NoSuchElementException

//scalastyle:off regex.println

/**
 *
 */
object AdvancedPatternMatchingPart2 extends App {
  // In this lecture we discuss about case classes can be written in infix notation
  // when used in case clauses, given the case class exactly two arguments

  case class Tour(src: String, dest: String)

  Tour("Chennai", "Delhi") match {
    // notice the infix notation
    case from Tour to => println(s"Tour is from $from to $to")
    case _ => println("No tour")
  }

  // When we decompose an object to a list of objects, we need to implement
  // unapplySeq method.
  abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
  }

  object EmptyList extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException("Empty list does not have head")
    def tail: MyList[Nothing] =
      throw new NoSuchElementException("Empty list does not have a tail")
  }

  case class MyConcreteList[+A](override val head: A, override val tail: MyList[A])
    extends MyList[A]

  object MyList {
    def unapplySeq[A](obj: MyList[A]): Option[Seq[A]] =
      if (obj == EmptyList) Some(Seq.empty[A])
      else unapplySeq(obj.tail).map(obj.head +: _)
  }

  val sampleMyList = MyConcreteList(1, MyConcreteList(2, MyConcreteList(3, EmptyList)))

  sampleMyList match {
    case MyList(1, 2, _*) => println(s"List starts with 1 and 2")
    case _ => ()
  }

  //=====================================================================
  // unapply method can return any value that implements both isEmpty and get methods
  class CustomUnapplyReturn[T](private val value: T) {
    def isEmpty: Boolean = false
    def get: T = this.value
  }

  case class Person(name: String)

  object Person {
    def unapply(person: Person): CustomUnapplyReturn[String] =
      new CustomUnapplyReturn(person.name)
  }

  Person("bob") match {
    case Person(name) => println(s"Person name is $name")
    case _ => println("Not a person")
  }
}
