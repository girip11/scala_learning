package org.scala.rockthejvm.basic.fp

abstract class MyList[+A] {
  val head: A
  val tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = s"[${this.printElements.trim()}]"
  def foreach(processor: A => Unit): Unit
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

  override def foreach(processor: Nothing => Unit): Unit = Unit

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

//    This is rewritten using the map operation
  println(for {
    n <- mylist
  } yield n * 2)

  //    This is rewritten using the foreach operation
  for {
    n <- mylist
  } println(n)

  Option
}
