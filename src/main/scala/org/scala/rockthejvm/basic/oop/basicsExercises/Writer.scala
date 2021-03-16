package org.scala.rockthejvm.basic.oop.basicsExercises

class Writer(firstname: String, surname: String, val yearOfBirth: Int) {
  def fullname: String = s"$firstname $surname"
}
