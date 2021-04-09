package org.scala.rockthejvm.advanced.advfp

// scalastyle:off regex.println

object Currying extends App {
  // Currying is when a function returns another function
  // Scala provides a syntactic sugar for currying using multiple parameter lists

  // Notice this superAdder is a method of the Currying singleton instance
  def superAdder(x: Int)(y: Int): Int = x + y

  // using currying we have transformed the method to a partially applied function
  // converting method to function is called lifting
  val add4: Int => Int = superAdder(4)

  // another way to defining a partially applied functions
  val add7 = superAdder(7)(_)

  // ETA expansion - Create functions out of methods.
  // functions are the variables that are passed around
  // compiler does the ETA expansion
  def inc(x: Int): Int = x + 1

  // Here inc is a method. Compiler converts this method to a function by
  // rewriting as x => inc(x)
  List(1, 2, 3, 4).map(inc)

  // partial function applications - we can force compiler to do ETA expansions
  // add5 is a function Int => Int
  // underscore forces the compiler to do the ETA expansion
  val add5 = superAdder(5) _

  // Partially applied functions - Partial Function Application
  // Exercises
  def formatNumber(fmt: String)(n: Double): String = fmt.format(n)

  val f1 = formatNumber("%4.2f")(_)
  val f2 = formatNumber("%8.1f")(_)

  println(List(1.0, 2.0, 3.0, Math.PI).map(f1))
  println(List(1.0, 2.0, 3.0, Math.PI).map(f2))

  //2. Difference between functions and methods
  def noparenMethod: Int = 42
  def parenMethod(): Int = 42

  // by-name vs 0-lambda
  def byName(exp: => Int): Int = exp
  def noArgLambda(l: () => Int): Int = l()

  // call byName and noArgLambda by passing methods in various ways
  println(byName(42)) // plain integer is also an expression
  println(byName(noparenMethod))
  println(byName(parenMethod)) // parenMethod called first, result is passed as exp
  println(byName(parenMethod())) // parenMethod called first, result is passed as exp

  //NOTE - Functions cannot be passed where by name parameters are expected.

  println(noArgLambda(() => noparenMethod))
  println(noArgLambda(parenMethod))
  println(noArgLambda(() => parenMethod()))
  println(noArgLambda(() => 42))
}
