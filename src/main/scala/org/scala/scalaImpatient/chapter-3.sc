import scala.collection.mutable.ArrayBuffer
//1. code snippet that sets a to an array of n random integers between 0
//(inclusive) and n (exclusive).

val n = 10
val a = new Array[Int](n)
val random = scala.util.Random

for {
  i <- 0 until n
} a(i) = random.nextInt(n)

a.foreach(println(_))

//2. Write a loop that swaps adjacent elements of an array of integers. For example,
//Array(1, 2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5).

var arr = Array(1, 2, 3, 4, 5)

for {
  i <- 0 until arr.length by 2
} if (i + 1 < arr.length) {
  val temp = arr(i)
  arr(i)= arr(i + 1)
  arr(i + 1) = temp
}

arr.foreach(println(_))

//3. Repeat the preceding assignment, but produce a new array with the swapped
//values. Use for/yield.

arr = Array(1, 2, 3, 4, 5)

val newList = for {
  i <- arr.indices
} yield if( i % 2 == 0) {
  if (i + 1 < arr.length) arr(i + 1) else arr(i)
  } else arr(i - 1)

newList.foreach(println(_))

// 4. Given an array of integers, produce a new array that contains all positive
// values of the original array, in their original order, followed by all values that
//are zero or negative, in their original order.

val posNegArr = Array(1, -2, 3, -4, 5, 0)
val resultArr = Array.ofDim[Int](posNegArr.length)

var count: Int = 0
for {
  i <- posNegArr.indices if (posNegArr(i) > 0)
} {
  resultArr(count) = posNegArr(i)
  count += 1
}

for {
  i <- posNegArr.indices if (posNegArr(i) <= 0)
} {
  resultArr(count) = posNegArr(i)
  count += 1
}

resultArr.foreach(println(_))

//5. How do you compute the average of an Array[Double]?
val doubleArr = Array(1.0, 2.0, 3.0, 4.0, 5.0)
println(s"Average: ${doubleArr.sum / doubleArr.length}")

//6. How do you rearrange the elements of an Array[Int] so that they appear in
//reverse sorted order? How do you do the same with an ArrayBuffer[Int]?

val unsortedArray = Array(1,5,2,4,3)
unsortedArray.sortWith(_ > _).foreach(println(_))
val unsortedArrayBuf = ArrayBuffer(1,5,2,4,3)
unsortedArrayBuf.sortWith(_ > _).foreach(println(_))

//7. Write a code snippet that produces all values from an array with duplicates
//removed. (Hint: Look at Scaladoc.)

val dupArray = Array(1,5,2,5,3, 5, 3, 2)
println(dupArray.distinct.foreach(println(_)))
// or
println(dupArray.toSet)

//8. Suppose you are given an array buffer of integers and want to remove all but
//the first negative number. Here is a sequential solution that sets a flag
//when te first negative number is called, then removes all elements beyond.

def removeNegativeExceptFirst(arrayBuf: ArrayBuffer[Int]): ArrayBuffer[Int] = {
    val negIndices = arrayBuf.indices.filter(i => arrayBuf(i) < 0)
      .drop(1)
      .reverse

    negIndices.foreach(arrayBuf.remove(_))
    arrayBuf
}

removeNegativeExceptFirst(ArrayBuffer(1, 2, 3, -1, 4, 5, -2, 6, -7, -3, 7))


//10. Make a collection of all time zones returned by java.util.TimeZone.getAvailableIDs
//that are in America. Strip off the "America/" prefix and sort the result.

import java.util.TimeZone.getAvailableIDs

val americanTimeZones = getAvailableIDs().filter(_.startsWith("America")).
  map(_.stripPrefix("America/")).
  sorted

americanTimeZones.foreach(println(_))


//11. Import java.awt.datatransfer._ and make an object of type SystemFlavorMap with
//the call
//val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
//Then call the getNativesForFlavor method with parameter DataFlavor.imageFlavor
//and get the return value as a Scala buffer. (Why this obscure class? It’s hard
//to find uses of java.util.List in the standard Java library.)

import java.awt.datatransfer._
//This is deprecated
import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.mutable.Buffer

val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
val scalaBuffer: Buffer[String] = flavors.getNativesForFlavor(DataFlavor.imageFlavor)

//Latest solution
import scala.collection.JavaConverters.asScalaBufferConverter
val scalaBuffer: Buffer[String] = flavors.getNativesForFlavor(DataFlavor.imageFlavor).asScala
