package org.scala.rockthejvm.basic.oop.basicsExercises

class Novel(name: String, yearOfRelease: Int, author: Writer) {

  def authorAge: Int = this.yearOfRelease - this.author.yearOfBirth

  def isWrittenBy(authorName: String): Boolean = (this.author.fullname == authorName)

  def copy(newYearOfRelease: Int): Novel =
    new Novel(this.name, newYearOfRelease, this.author)
}
