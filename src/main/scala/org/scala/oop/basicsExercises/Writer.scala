package org.scala.oop.basicsExercises

class Writer(firstname: String, surname: String, val yearOfBirth: Int) {
    def fullname: String = s"$firstname $surname"
}
