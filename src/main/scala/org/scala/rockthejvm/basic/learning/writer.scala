package org.scala.rockthejvm.basic.learning

class Writer(firstName: String, surname: String, val birthYear: Int) {
  def fullname(): String = s"$firstName $surname"
}
