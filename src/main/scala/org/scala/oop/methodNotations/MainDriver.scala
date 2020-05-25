package org.scala.oop.methodNotations

object MainDriver extends App {

    val mary = new Person("Mary", 25, "Inception")
    println(mary learns "Python")
    println(mary learnsScala)
    println((mary + "Rockstar").name)
    println((+mary).age)
    println(mary(3))
}
