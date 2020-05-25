package org.scala.oop.inheritance

import scala.annotation.tailrec

abstract class MyList[+A] {
  val head: A
  val tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def +[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = s"[${this.printElements.trim()}]"
}

object EmptyList extends MyList[Nothing] {

  override lazy val head: Nothing = throw new NoSuchElementException

  override lazy val tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyList[B] = new MyConcreteList(element, this)

  override def +[B >: Nothing](element: B): MyList[B] = this.add(element)

  override def printElements: String = ""
}


class MyConcreteList[+A](override val head: A, override val tail: MyList[A]) extends MyList[A] {

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyList[B] = new MyConcreteList(element, this)

  override def +[B >: A](element: B): MyList[B] = this.add(element)

//  override protected def printElements: String = {
//      @tailrec
//      def iterate(list: MyList, acc: String): String = {
//        if(list.isEmpty) acc
//        else iterate(list.tail, s"$acc ${list.head}")
//      }
//
//      iterate(this, "")
//  }

  override def printElements: String = s"${this.head} ${tail.printElements}"
}
