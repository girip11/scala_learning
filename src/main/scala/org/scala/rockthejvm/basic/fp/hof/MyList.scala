package org.scala.rockthejvm.basic.fp.hof

import scala.util.Random

abstract class MyList[+A] {
  val head: A
  val tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = s"[${this.printElements.trim()}]"
  def foreach(processor: A => Unit): Unit
  def zipWith[B, C](list: MyList[B], transformer: (A, B) => C): MyList[C]
  def fold[B](start: B)(transformer: (B, A) => B): B
  def sort(compare: (A, A) => Int): MyList[A]
  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object EmptyList extends MyList[Nothing] {

  override lazy val head: Nothing = throw new NoSuchElementException

  override lazy val tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyList[B] =
    new MyConcreteList(element, this)

  override def printElements: String = ""

  override def foreach(processor: Nothing => Unit): Unit = ()

  override def zipWith[B, C](list: MyList[B], transformer: (Nothing, B) => C): MyList[C] =
    if (list.isEmpty) EmptyList
    else throw new RuntimeException("Cannot zip lists of varying length")

  override def fold[B](start: B)(transformer: (B, Nothing) => B): B = start

  override def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = EmptyList

  override def filter(predicate: Nothing => Boolean): MyList[Nothing] =
    EmptyList

  override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] =
    EmptyList

  override def map[B](transformer: Nothing => B): MyList[B] =
    EmptyList

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

case class MyConcreteList[+A](override val head: A, override val tail: MyList[A])
  extends MyList[A] {

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyList[B] = new MyConcreteList(element, this)

  override def printElements: String = s"${this.head} ${tail.printElements}"

  override def foreach(processor: A => Unit): Unit = {
    processor(this.head)
    this.tail.foreach(processor)
  }

  override def zipWith[B, C](list: MyList[B], transformer: (A, B) => C): MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("Cannot zip lists of varying length")
    else
      new MyConcreteList(
        transformer(this.head, list.head),
        this.tail.zipWith(list.tail, transformer))
  }

  override def fold[B](start: B)(transformer: (B, A) => B): B =
    this.tail.fold(transformer(start, this.head))(transformer)

//  Sort is not tail recursive
  override def sort(compare: (A, A) => Int): MyList[A] = {

    @scala.annotation.tailrec
    def insert(x: A, sortedList: MyList[A], acc: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) acc ++ MyConcreteList(x, EmptyList)
      else if (compare(x, sortedList.head) <= 0) acc ++ MyConcreteList(x, sortedList)
      else insert(x, sortedList.tail, acc ++ MyConcreteList(sortedList.head, EmptyList))
    }

    val sortedList = this.tail.sort(compare)
//    println(s"$head, $sortedList")
    insert(this.head, sortedList, EmptyList)
  }

  override def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(this.head)) new MyConcreteList(this.head, this.tail.filter(predicate))
    else this.tail.filter(predicate)

  override def map[B](transformer: A => B): MyList[B] =
    new MyConcreteList(transformer(this.head), this.tail.map(transformer))

  override def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(this.head) ++ this.tail.flatMap(transformer)

  override def ++[B >: A](list: MyList[B]): MyList[B] =
    new MyConcreteList(this.head, this.tail ++ list)
}

object MyListDriver extends App {
  val mylist = new MyConcreteList[Int](4, EmptyList).add(3).add(2).add(1)
  println(mylist.filter((x: Int) => x % 2 == 0))
  println(mylist.map(_ * 2))
  println(mylist.flatMap(x => new MyConcreteList[Int](x * 10, EmptyList).add(x)))

  val myAnotherList = new MyConcreteList[Int](8, EmptyList).add(7).add(6).add(5)

//  zipWith can be used in either ways
  println(mylist.zipWith(myAnotherList, (x: Int, y: Int) => x * y))
  println(mylist.zipWith[Int, Int](myAnotherList, _ * _))

  println(mylist.fold(100)(_ + _))

  val unsortedList =
    new MyConcreteList[Int](4, EmptyList).add(20).add(100).add(10).add(50)
  println(unsortedList)
  println(unsortedList.sort((x, y) => x - y))
  //  val unsortedList = new MyConcreteList[Int](1, EmptyList).add(2).add(3).add(4).add(5)

//  This will raise stackoverflow exception since the sort function is not tail recursive
//  var unsortedList: MyList[Int] = new MyConcreteList[Int](1, EmptyList)
//  val r = new Random((System.nanoTime()))
//  for {
//   n <- 1 to 100000
//  } unsortedList = unsortedList.add(r.nextInt(Int.MaxValue))
//  val sortedList = unsortedList.sort((x,y) => x-y)
//  sortedList.fold(0)( (x, y) => {
//    if (x <= y) y
//    else throw new RuntimeException("Problem detected")
//  })

  //    This is rewritten using the map operation
  println(for {
    n <- mylist
  } yield n * 2)

  //    This is rewritten using the foreach operation
  for {
    n <- mylist
  } println(n)

}
