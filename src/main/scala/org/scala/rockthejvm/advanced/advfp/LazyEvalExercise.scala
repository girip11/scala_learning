package org.scala.rockthejvm.advanced.advfp

// scalastyle:off regex.println

object LazyEvalExercise extends App {
  /*
  EXERCISE: Implement a lazily evaluated singly linked list stream of elements.
   */
  abstract class MyStream[+A] {
    def isEmpty: Boolean
    def head: A
    def tail: MyStream[A]

    def #::[B >: A](element: B): MyStream[B]
    def ++[B >: A](anotherStream: MyStream[B]): MyStream[B]

    def foreach(f: A => Unit): Unit
    def map[B](f: A => B): MyStream[B]
    def flatMap[B](f: A => MyStream[B]): MyStream[B]
    def filter(predicate: A => Boolean): MyStream[A]

    def take(n: Int): MyStream[A]
    def takeAsList(n: Int): List[A]
  }

  object MyStream {
    def from[A](start: A)(generator: A => A): MyStream[A] = {
      println(s"Called with start: $start")
      new MyConcreteStream[A](start, from(generator(start))(generator))
    }
  }

  class MyEmptyStream[A] extends MyStream[A] {
    override def isEmpty: Boolean = true

    override def head: A = throw new NoSuchElementException("No head on empty stream")

    override def tail: MyStream[A] = throw new NoSuchElementException("No tail on empty stream")

    override def #::[B >: A](element: B): MyStream[B] = new MyConcreteStream[B](element, this)

    override def ++[B >: A](anotherStream: MyStream[B]): MyStream[B] = anotherStream

    override def foreach(f: A => Unit): Unit = ()

    override def map[B](f: A => B): MyStream[B] = new MyEmptyStream[B]

    override def flatMap[B](f: A => MyStream[B]): MyStream[B] = new MyEmptyStream[B]

    override def filter(predicate: A => Boolean): MyStream[A] = this

    override def take(n: Int): MyStream[A] = {
      if (n == 0)
        this
      else
        throw new IndexOutOfBoundsException(s"Unable to take $n elements from empty stream")
    }

    override def takeAsList(n: Int): List[A] = {
      if (n == 0)
        Nil
      else
        throw new IndexOutOfBoundsException(s"Unable to take $n elements from empty stream")
    }
  }

  class MyConcreteStream[A](override val head: A, tailExp: => MyStream[A]) extends MyStream[A] {

    override def isEmpty: Boolean = false

    // lazy val is preferred because we want the tailExp to be evaluated exactly once
    // in this instance.
    override lazy val tail: MyStream[A] = tailExp

    override def #::[B >: A](element: B): MyStream[B] = new MyConcreteStream[B](element, this)

    override def ++[B >: A](anotherStream: MyStream[B]): MyStream[B] = new MyConcreteStream(head, tail ++ anotherStream)

    override def foreach(f: A => Unit): Unit = {
      f(head)
      tail.foreach(f)
    }

    override def map[B](f: A => B): MyStream[B] = {
      new MyConcreteStream(f(head), tail.map(f))
    }

    override def flatMap[B](f: A => MyStream[B]): MyStream[B] = {
      val st = f(head)
      new MyConcreteStream(st.head, st.tail ++ tail.flatMap(f))
    }

    override def filter(predicate: A => Boolean): MyStream[A] = {
      if (predicate(head))
        new MyConcreteStream(head, tail.filter(predicate))
      else
        tail.filter(predicate)
    }

    override def take(n: Int): MyStream[A] = {
        if (n == 0)
          new MyEmptyStream[A]
        else
          new MyConcreteStream(head, tail.take(n-1))
    }

    override def takeAsList(n: Int): List[A] = {
      if (n == 0)
        Nil
      else {
        head :: tail.takeAsList(n-1)
      }
    }

    override def toString: String = {
      s"($head, ?)"
    }
  }

//  val st = MyStream.from(5)(_ + 1)
//  println(st)
//  println(st.head)
//  println(st.tail)
//  println(st.tail.head)
//  println(st.takeAsList(10))
//
//  println(1 #:: st)
//  println((1 #:: st).takeAsList(3))
//
//  println(((1 #:: new MyEmptyStream[Int]) ++ st).takeAsList(10))
//
//  st.take(5).foreach(println)
//
//  st.take(5).map(_ * 10).foreach(println)
//
//  st.map(_ * 10).take(5).foreach(println)
//
//  st.flatMap(i => (i * 10) #:: new MyEmptyStream[Int]).take(5).foreach(println)
//
//  st.filter(_ % 2 == 0).take(5).foreach(println)

  def getFibonacciStream(f: Int, s: Int): MyStream[Int] = {
    new MyConcreteStream[Int](f, getFibonacciStream(s, f + s))
  }
  val fibSt = getFibonacciStream(1, 1)
  fibSt.take(10).foreach(println)

  println("================================================================================")
  //Stream of prime numbers with Eratosthenes Sieve
  def getPrimeNumbersStream: MyStream[Int] = {
    def getPrimeInner(prime: Int, stream: MyStream[Int]): MyStream[Int] = {
      val nextStream = stream.filter(n => n != prime && n % prime != 0)
      val nextPrime = nextStream.take(1).head
      new MyConcreteStream[Int](prime, getPrimeInner(nextPrime, nextStream))
    }
    getPrimeInner(2, MyStream.from(2)(_ + 1))
  }

  val primeStream = getPrimeNumbersStream
  primeStream.take(10).foreach(println)
}
