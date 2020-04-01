package org.scala.oop

trait MyPredicate[T] {
  def test(value: T): Boolean
}

class EvenPredicate extends MyPredicate[Int] {
  def test(value: Int): Boolean = (value % 2 == 0)
}
