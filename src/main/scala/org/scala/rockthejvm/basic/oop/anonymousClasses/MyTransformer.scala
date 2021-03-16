package org.scala.rockthejvm.basic.oop.anonymousClasses

trait MyTransformer[-A, B] {
  def transform(element: A): B
}

class StringToIntTransformer extends MyTransformer[String, Int] {
  override def transform(element: String): Int = element.toInt
}

class NextNumberTransformer extends MyTransformer[Int, MyList[Int]] {
  override def transform(element: Int): MyList[Int] =
    new MyConcreteList[Int](element + 1, EmptyList).add(element)
}
