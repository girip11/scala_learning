
//1. Write an example program to demonstrate that
//package com.horstmann.impatient
//is not the same as
//package com
//package horstmann
//package impatient

//For instance, if you have TestClass defined under horstmann/com
//using the second syntax you cannot define TestClass impatient package
//while with the first syntax we can have TestClass defined under horstmann
// and impatient package without conflicts

//2. Write a puzzler that baffles your Scala friends, using a package com that isn’t
//at the top level.

//3. Write a package random with functions nextInt(): Int, nextDouble(): Double,
//and setSeed(seed: Int): Unit. To generate random numbers, use the linear
//congruential generator
//next = (previous × a + b) mod 2n,
//where a = 1664525, b = 1013904223, n = 32, and the initial value of previous
//is seed.

//package object random {
//
//  private val a = 1664525
//  private val b = 1013904223
//  private val n = 32
//  private var seed: Int = 1
//  private var lastInt: Int = seed
//  private var lastDouble: Double = seed
//
//  def setSeed(seed: Int) {
//    this.seed = seed
//    this.lastInt = seed
//    this.lastDouble = seed
//  }
//
//  def nextInt(): Int = {
//    val newInt: Int = (lastInt * a + b) % math.pow(2.0, n).toInt
//    lastInt = newInt
//    newInt
//  }
//
//  def nextDouble(): Double = {
//    val newDouble: Double = (lastDouble * a + b) % math.pow(2.0, n)
//    lastDouble = newDouble
//    newDouble
//  }
//}

//4. Why do you think the Scala language designers provided the package object
//syntax instead of simply letting you add functions and variables to a package?

//Answer: packages are open. package objects are closed
// There can be only one package object per package. package objects
// enforce to have all the members of the package in the same file

//5. What is the meaning of private[com] def giveRaise(rate: Double)? Is it useful?

//Answer: Useful for defining package private variables. In other words to achieve
//package visibility. giveRaise is now accessible to all objects under com package
//including the subpackages under com(nested hierarchy)

//6. Write a program that copies all elements from a Java hash map into a Scala
//hash map. Use imports to rename both classes.

import java.util.{HashMap => JHashMap}
import scala.collection.mutable.HashMap


//Just an example to show the imports with aliases
def copy(in: JHashMap[String, Int]): HashMap[String, Int] = {
  val map = HashMap.empty[String, Int]
  in forEach { (k, v) => map.put(k, v)}
  map
}

//7. In the preceding exercise, move all imports into the innermost scope possible.

//Answer: From top level, we can move all the imports to the object containing the copy method

//8. What is the effect of
//import java._
//import javax._
//Is this a good idea?

//Answer: This is not a good idea. If java and javax have
// subpackages or class or object with same name
//we will have conflicts in using them
// Also unnecessarily we have imported a lot of names in this scope

//9. Write a program that imports the java.lang.System class, reads the user name
//from the user.name system property, reads a password from the StdIn object,
//and prints a message to the standard error stream if the password is not
//"secret". Otherwise, print a greeting to the standard output stream. Do not
//use any other imports, and do not use any qualified names (with dots).

import java.lang.System

println(System.getProperty("user.name"))

val password = io.StdIn.readLine()
if (password != "secret") {
  System.err.println(password)
} else {
  println("Hello")
}


//10. Apart from StringBuilder, what other members of java.lang does the scala
//package override?

// Types like Float, Byte, Char, Short are also overridden