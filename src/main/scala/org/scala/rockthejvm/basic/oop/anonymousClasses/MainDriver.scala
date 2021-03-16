package org.scala.rockthejvm.basic.oop.anonymousClasses

object MainDriver extends App {

  val integersList =
    new MyConcreteList[Int](1, EmptyList).add(2).add(3).add(4).add(5).add(6)

//    Testing filter
  println(EmptyList.filter(new EvenPredicate))
  println(integersList.filter(new EvenPredicate))
  println(integersList.filter(new MyPredicate[Int] {
    override def test(element: Int): Boolean = element % 2 != 0
  }))

//  Testing map
  println(integersList.map(new MyTransformer[Int, Int] {
    override def transform(element: Int): Int = element * 2
  }))

//  Testing flatmap operation
  println(integersList.flatMap(new NextNumberTransformer))

  val sameIntegerList =
    new MyConcreteList[Int](1, EmptyList).add(2).add(3).add(4).add(5).add(6)

//    Equals and hashcode are present due to MyConcreteList being a case class
  println(integersList == sameIntegerList)
}
