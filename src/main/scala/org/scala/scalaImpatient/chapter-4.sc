
//1. Set up a map of prices for a number of gizmos that you covet. Then produce
//a second map with the same keys and the prices at a 10 percent discount.

val originalGizmos = Map("A" -> 100.0, "B" -> 200.0, "C" -> 300.0)
val discountedGizmos = originalGizmos.mapValues(price => price - (price * 0.1))

println(discountedGizmos)

//2. Write a program that reads words from a file. Use a mutable map to count
//how often each word appears. To read the words, simply use a java.util.Scanner:
//val in = new java.util.Scanner(new java.io.File("myfile.txt"))
//while (in.hasNext()) process in.next()

import java.util

import scala.collection.mutable.{Map => MutableMap}

val words: MutableMap[String, Int] = MutableMap.empty

//Instead of reading from file, I will read the content from a variable.
// val contents = scala.io.Source.fromFile("input.txt").getLines.mkString.split(' ')

val contentsOfFile =
  """
    |Lorem ipsum dolor sit amet consectetur adipisicing elit
    |Cumque ipsum dolor asperiores amet cum adipisicing elit
    |""".stripMargin

for {
  word <- contentsOfFile.split(' ')
} {
  val wordCount = words.getOrElse(word, 0)
  words(word) = wordCount + 1
}

println(words)

//3. Repeat the preceding exercise with an immutable map
var immutableWords = Map.empty[String, Int].withDefaultValue(0)

for {
  word <- contentsOfFile.split(' ')
} {
  val wordCount: Int = immutableWords.getOrElse(word, 0)
  immutableWords += word -> (wordCount + 1)
}

println(immutableWords)

//4. Repeat the preceding exercise with a sorted map, so that the words are
//printed in sorted order.

import scala.collection.mutable.SortedMap

val sortedWords = SortedMap.empty[String, Int]

contentsOfFile.split(' ').foreach(word => {
  val wordCount = sortedWords.getOrElse(word, 0)
  sortedWords(word) = wordCount + 1
})

println(sortedWords)

//5. Repeat the preceding exercise with a java.util.TreeMap that you adapt to the
//Scala API.

import java.util.TreeMap
import scala.collection.JavaConverters.mapAsScalaMapConverter

val javaTreeMapWords = new TreeMap[String, Int]().asScala

contentsOfFile.split(' ').foreach(word => {
  val wordCount = javaTreeMapWords.getOrElse(word, 0)
  javaTreeMapWords(word) = wordCount + 1
})

println(javaTreeMapWords)


//6. Define a linked hash map that maps "Monday" to java.util.Calendar.MONDAY, and
//similarly for the other weekdays. Demonstrate that the elements are visited
//in insertion order.
import scala.collection.mutable.LinkedHashMap

val daysOfWeek = LinkedHashMap("Monday" -> java.util.Calendar.MONDAY,
  "Tuesday" -> java.util.Calendar.TUESDAY,
  "Wednesday" -> java.util.Calendar.WEDNESDAY,
  "Thursday" -> java.util.Calendar.THURSDAY,
  "Friday" -> java.util.Calendar.FRIDAY,
  "Saturday" -> java.util.Calendar.SATURDAY,
  "Sunday" -> java.util.Calendar.SUNDAY)

for {
  (day, _) <- daysOfWeek
} println(day)

//7. Print a table of all Java properties reported by the getProperties method of the
//java.lang.System class, like this:
//java.runtime.name       | Java(TM) SE Runtime Environment
//sun.boot.library.path   | /home/apps/jdk1.6.0_21/jre/lib/i386
//java.vm.version         | 17.0-b16
//java.vm.vendor          | Sun Microsystems Inc.
//java.vendor.url         | http://java.sun.com/
//path.separator          | :
//java.vm.name            | Java HotSpot(TM) Server VM
//You need to find the length of the longest key before you can print the table.

import scala.collection.JavaConverters.propertiesAsScalaMapConverter

val propsScala: scala.collection.Map[String, String] = System.getProperties().asScala
val maxKeyLength:Int = propsScala.keySet.map(_.length).max

for {
  (key, value) <- propsScala
} println(s"$key${" " * (maxKeyLength - key.length)}| $value")

//8. Write a function minmax(values: Array[Int]) that returns a pair containing the
//smallest and the largest values in the array.

def minmax(values: Array[Int]): (Int, Int) = {
  if (values.isEmpty)
    throw new IllegalArgumentException("List cannot be empty")

  (values.min, values.max)
}

println(minmax(Array(1,2,3,4,5)) == (1,5))

//9. Write a function lteqgt(values: Array[Int], v: Int) that returns a triple containing
//the counts of values less than v, equal to v, and greater than v.

def lteqgt(values: Array[Int], v: Int): (Int, Int, Int) = {
  if (values.isEmpty)
    throw new IllegalArgumentException("List cannot be empty")

  (values.count(_ < v),
    values.count(_ == v),
    values.count(_ > v))
}

println(lteqgt(Array(1,2,3,4,5,6), 3) == (2, 1, 3))

//10. What happens when you zip together two strings, such as "Hello".zip("World")?
//Come up with a plausible use case.

//produces a list of pairs
//[('h', 'w'),
//  ('e', 'o'),
//  ('l', 'r'),
//  ('l', 'l'),
//  ('o', 'd')]
println("Hello".zip("World"))