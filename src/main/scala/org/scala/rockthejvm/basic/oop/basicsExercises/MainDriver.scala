package org.scala.rockthejvm.basic.oop.basicsExercises

object MainDriver extends App {

  // Exercise 1
  val author: Writer = new Writer("Charles", "Dickens", 1812)
  val novel: Novel = new Novel("Adventures of Tom Sawyer", 1861, author)
  println(novel.authorAge)
  println(novel.isWrittenBy(author.fullname))

  // Exercise 2
  val counter = new Counter(5)

  println(counter.currentCount)

  val newCounter = counter + 5
  println(newCounter.currentCount)
}
