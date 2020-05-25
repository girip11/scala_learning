package org.scala.learning

class Novel(name: String, releaseYear: Int, author: Writer) {

  // Can omit paranthesis if the method has no arguments
  def authorAge: Int = releaseYear - this.author.birthYear

  def isWrittenBy(author: Writer): Boolean =
    author == this.author

  def copy(releaseYear: Int): Novel = new Novel(name, releaseYear, author)
}
