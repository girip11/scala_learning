package org.scala.rockthejvm.basic.oop.inheritance

object MainDriver extends App {
//    val mylist = new MyConcreteList(1, EmptyList)
//    val mylist = EmptyList + 1
//    println(mylist)
//    println(mylist.add(2))

  val listOfIntegers = new MyConcreteList[Int](1, EmptyList)
  println(listOfIntegers)
  println(listOfIntegers.add(2))
}
