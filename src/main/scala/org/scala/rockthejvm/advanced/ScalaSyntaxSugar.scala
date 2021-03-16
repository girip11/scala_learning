package org.scala.rockthejvm.advanced

import scala.util.Try

//scalastyle:off regex.println

/**
 * Syntactic sugars in Scala
 */
object ScalaSyntaxSugar extends App {

  // 1. Methods with single parameter can be called
  // with the result of the block
  def sayHello(name: String): Unit = println(s"Hello, $name")

  sayHello {
    // some logic to arrive at a string
    List("J", "o", "h", "n").mkString
  }

  // Try's apply method called with a block, but this time using
  // call by name. Here the entire block is passed as expression to the
  // apply method.
  val someValue = Try {
    println("Some logic inside try")
  }

  //=====================================================================
  //  2. Single Abstract Method(SAM) interfaces with lambda functions
  // This applies to both traits and abstract classes with exactly one abstract method
  trait SomeInterface {
    def doSomething(value: Int): String
  }

  def execute(someInterface: SomeInterface, input: Int): String =
    someInterface.doSomething(input)

  // Notice we are assigning lambda methods to variables of type SomeInterface
  val someInterface: SomeInterface = (_: Int).toString
  println(someInterface.doSomething(100))
  println(execute((x: Int) => x.toString, 200))

  //======================================================================
  // 3. :: and #::
  // operators ending with : are right associative
  // Nil.::(2).::(1)
  val sampleList = 1 :: 2 :: Nil
  println(sampleList)

  // Also case classes can be written in infix notation in case clauses
  // In general, we can use infix notation, when the unapply method returns
  // two parameters or a class class accepts two parameters in its constructor.
  case class To(src: String, dest: String)
  To("NewYork", "London") match {
    case src To dest => println(s"From $src to $dest")
    case _ => ()
  }

  //======================================================================
  // 4: Multiword naming using backticks
  case class Person(name: String) {
    def `said that`(msg: String) = println(s"$name said that $msg")
  }

  val jane = Person("Jane")
  jane `said that` "She likes to play Badminton"

  //======================================================================
  // 5. Generic type and infix notations
  class MyKeyValueStore[K, V]
  lazy val kvstore: MyKeyValueStore[String, Int] = ???
  lazy val kvstoreInfix: String MyKeyValueStore Int = ???

  //======================================================================
  // 6. apply and update method
  // update method is used in mutable collections
  import scala.collection.mutable.Map
  class CountryAndCapital {
    private val words = Map[String, String]()
    def apply(index: String): String = words(index)
    def update(index: String, value: String): Unit = words(index) = value
  }

  val capitals = new CountryAndCapital()
  capitals("India") = "NewDelhi"
  println(capitals("India"))

  //======================================================================
  // 7. Custom setter for mutable variables
  class MutableContainer {
    private var word = ""
    def wordOfTheDay: String = this.word
    def wordOfTheDay_=(value: String): Unit =
      this.word = value
  }

  val mutContainer = new MutableContainer
  mutContainer.wordOfTheDay = "Exile"
  println(mutContainer.wordOfTheDay)
}
