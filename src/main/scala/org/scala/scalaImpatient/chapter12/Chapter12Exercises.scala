package org.scala.scalaImpatient.chapter12

// scalastyle:off regex.println

object Chapter12Exercises extends App {

  //1. Write a function values(fun: (Int) => Int, low: Int, high: Int)
  // that yields a collection of function inputs and outputs in a given range.
  // For example, values(x => x * x, -5, 5) should produce
  // a collection of pairs (-5, 25), (-4, 16),
  // (-3, 9), . . . , (5, 25).
  def values(fun: (Int) => Int, low: Int, high: Int): Iterable[(Int, Int)] = {
    val result: List[(Int, Int)] = List.empty[(Int, Int)]
    if (low > high) result
    else {
      (result :+ (low, fun(low))) ++ values(fun, low + 1, high)
    }
  }

  values(x => x * x, -5, 5).foreach(println)

  def valuesAlt(fun: (Int) => Int, low: Int, high: Int): Iterable[(Int, Int)] = {
    (low to high).map(n => (n, fun(n)))
  }

  //==============================================================================
  //2. How do you get the largest element of an array with reduceLeft?
  import scala.math.max
  println(Array(1,2,3,4,5).reduceLeft(max(_,_)))

  //==============================================================================
  //3. Implement the factorial function using to and reduceLeft, without a loop or
  //recursion.
  def factorial(n: Long): Long = if (n == 0) 1L
    else (1L to n).reduceLeft(_ * _)

  println(factorial(5))

  //==============================================================================
  //4. The previous implementation needed a special case when n < 1. Show how
  //you can avoid this with foldLeft. (Look at the Scaladoc for foldLeft. It’s like
  //reduceLeft, except that the first value in the chain of combined values is supplied
  //in the call.)

  def factorialFoldLeft(n: Long): Long = (1L to n).foldLeft(1L)(_ * _)
  println(factorialFoldLeft(0))
  println(factorialFoldLeft(5))

  //==============================================================================
  //5. Write a function largest(fun: (Int) => Int, inputs: Seq[Int])
  // that yields the largest value of a function within a given
  // sequence of inputs. For example, largest(x => 10 * x - x * x, 1 to 10)
  // should return 25. Don’t use a loop or recursion.
  def largest(fun: (Int) => Int, inputs: Seq[Int]): Int = {
    inputs.map(fun).max
  }

  // This finds the largest in single iteration
  def largestImp2(fun: (Int) => Int, inputs: Seq[Int]): Int = {
    inputs.foldLeft(Int.MinValue)(_ max fun(_))
  }

  println(largest(x => 10 * x - x * x, 1 to 10))
  println(largestImp2(x => 10 * x - x * x, 1 to 10))

  //==============================================================================
  //6. Modify the previous function to return the input at which
  // the output is largest. For example,
  // largestAt(x => 10 * x - x * x, 1 to 10) should return 5. Don’t use
  //a loop or recursion.

  def largestAt(fun: (Int) => Int, inputs: Seq[Int]): Int = {
    inputs.foldLeft((Int.MinValue, Option.empty[Int]))((maxResult, n) => {
      val result = fun(n)
      if (maxResult._2.isEmpty || maxResult._1 < result) (result, Some(n))
      else maxResult
    })._2.get
  }

  println(largestAt(x => 10 * x - x * x, 1 to 10))

  //==============================================================================
  //7. It’s easy to get a sequence of pairs, for example:
  //val pairs = (1 to 10) zip (11 to 20)
  //Now, suppose you want to do something with such a sequence—say, add
  //up the values. But you can’t do
  //pairs.map(_ + _)
  //The function _ + _ takes two Int parameters, not an (Int, Int) pair. Write a
  //function adjustToPair that receives a function of type (Int, Int) => Int and returns
  //the equivalent function that operates on a pair. For example, adjustToPair(_ *
  //_)((6, 7)) is 42.
  //Then use this function in conjunction with map to compute the sums of the
  //elements in pairs

  def adjustToPair(func: (Int, Int) => Int): ((Int, Int)) => Int =
    (x: (Int, Int)) => func(x._1, x._2)

  def adjustToPairUsingCurrying(func: (Int, Int) => Int)(pair: (Int, Int)): Int =
    func(pair._1, pair._2)

  println(adjustToPair(_ * _)((6, 7)))
  println(adjustToPairUsingCurrying(_ * _)((6, 7)))

  val pairs = (1 to 10) zip (11 to 20)
  // adjustToPair returns a lambda that operates on tuple
  // this lambda is passed to map which passes the tuple to compute the sum
  println(pairs.map(adjustToPair(_ + _)))
  println(pairs.map(adjustToPairUsingCurrying(_ + _)))

  //  8. In Section 12.8, “Currying,” on page 164, you saw the corresponds method used
  //with two arrays of strings. Make a call to corresponds that checks whether the
  //elements in an array of strings have the lengths given in an array of integers.

  println(Array("Hello", "World", "Foo", "Bar", "Awesome")
    .corresponds(Array(5,5,3,3,7))(_.length == _))

  //This should return false
  println(Array("Hello", "World", "Foo", "Bar", "Awesome")
    .corresponds(Array(5,5,3,4,3))(_.length == _))

  //==============================================================================
  //9. Implement corresponds without currying. Then try the call from the preceding
  //exercise. What problem do you encounter?
  // With generic types, lambda function requires explicit types
  def corresponds[A,B](seq1: Seq[A], seq2: Seq[B], func: (A, B) => Boolean): Boolean =
    seq1.length == seq2.length && seq1.zip(seq2).forall(t => func(t._1, t._2))

  println(
    corresponds(Array("Hello", "World", "Foo", "Bar", "Awesome"),
      Array(5,5,3,3,7),
      // I am unable to use the shorthand with type inference _.length == _
      (a: String, b: Int) => a.length == b))

  println(
    corresponds(Array("Hello", "World", "Foo", "Bar", "Awesome"),
      Array(5,5,3,3),
      (a: String, b: Int) => a.length == b))

  //==============================================================================
  //10. Implement an unless control abstraction that works just like if, but with an
  //inverted condition. Does the first parameter need to be a call-by-name
  //parameter? Do you need currying?

  // first parameter is a call by name
  // and we need currying to make it look like if structure
  //otherwise the call will look like unless(condition, block)
  def unless(condition: => Boolean)(block: => Unit): Unit = {
    if (!condition) {
      block
    }
  }

  val value = 5
  unless (value == 10) {
    println("This should be printed")
  }
}
