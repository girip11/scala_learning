package org.scala.oop

trait MyTransformer[A, B] {
  def transform(value: A): B
}

class StringToIntTransformer extends MyTransformer[String, Int] {
  def transform(value: String): Int = value.toInt
}
