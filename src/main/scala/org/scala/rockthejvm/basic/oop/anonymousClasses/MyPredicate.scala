package org.scala.rockthejvm.basic.oop.anonymousClasses

trait MyPredicate[-T] {
  def test(element: T): Boolean
}

class EvenPredicate extends MyPredicate[Int] {
  override def test(element: Int): Boolean = element % 2 == 0
}
