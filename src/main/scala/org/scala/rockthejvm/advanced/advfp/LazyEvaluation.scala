package org.scala.rockthejvm.advanced.advfp

// scalastyle:off regex.println

object LazyEvaluation extends App {
  println("Hello world")

  // when ex is first referenced, then the variable is evaluated
  lazy val ex = throw new RuntimeException("Exception")

  lazy val someValue: Int = {
    println("Hello")
    100
  }
  println(someValue) // evaluated for the first time and the value is stored
  println(someValue) // only returns the stored value.


  // call by need

  // Assume we have this method with a side effect
  def retrieveSomeValue: Int = {
    // sleep mimics heavy computation causing side effect
    // it could be some network call as well
    Thread.sleep(1000)
    println("Waiting")
    42
  }

  def byName(exp: => Int) : Int = {
    // below technique is referred to as call by need because
    // we evaluate the expression lazily once and use the value
    // returned internally many times.
    lazy val evaluated = exp

    // below statement mimics multiple usage of the expression
    (evaluated + evaluated) + 1
  }

  println(byName(retrieveSomeValue))

  // Lazy filtering using `withFilter`
  def lessThan30(i: Int): Boolean = {
    println(s"Is $i < 30?")
    i < 30
  }

  def greaterThan20(i: Int): Boolean = {
    println(s"Is $i > 20?")
    i > 20
  }

  val numbers = List(1, 25, 40, 5, 23)
  val lt30 = numbers.filter(lessThan30)
  val gt20 = lt30.filter(greaterThan20)
  gt20.foreach(println)

  // lazy filters
  // here each number goes through all the filters + the action(foreach in this case)
  // before proceeding with the next number
  val lazyLt30 = numbers.withFilter(lessThan30)
  val lazyGt20 = lazyLt30.withFilter(greaterThan20)
  // foreach forces the computation to take place
  lazyGt20.foreach(println)

  // for-comprehensions with if-guards are rewritten using withFilter
}
