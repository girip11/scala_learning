package org.scala.rockthejvm.basic.oop.methodNotations

class Person(val name: String, val age: Int, val favoriteMovie: String) {

  def +(nickName: String): Person =
    new Person(s"$name ($nickName)", this.age, this.favoriteMovie)

  def unary_+(): Person = new Person(this.name, this.age + 1, this.favoriteMovie)

  def learns(subject: String): Unit = println(s"$name learns $subject")

  def learnsScala: Unit = this.learns("Scala")

  def apply(n: Int): Unit = println(s"$name watched movie $favoriteMovie $n times")
}
