# Sequences

Here we discuss only the immutable collections

* well defined order
* can be indexed
* supports apply, length, indexing and iteration
* concatenation, append, prepend
* grouping,sorting, slicing, zipping

## `Seq`

```Scala
val aSeq = Seq(1,2,3,4)

// default seq is a List(immutable)
println(aSeq)

// Returns a new list with the order of elements reversed
println(aSeq.reverse)

// apply method used to fetch an element at a particular index
println(aSeq(2))

// concatentation
println(aSeq ++ Seq(7, 4, 5))

//sorting
println(Seq(1,5,4,2).sorted)
```

## `Range`

```Scala
// inclusive range
val aRange: Seq[Int] = 1 to 10

val exclusiveRange: Seq[Int] = 1 until 10

aRange.foreach(println)
```

## `List`

* Get head, tail and isEmpty methods are O(1)
* length, reverse, updating by index all take long O(n)

```Scala
val aList = List(1, 2, 3, 4)

val prepended = 42 :: aList
println(prepended)

// colon is always list facing
val anotherPrepended = 42 +: aList

// list appending
val appended = aList :+ 100

// fills 4 elements of list with value apple
val apples5 = List.fill(5)("apple")
println(apples5)
println(apples5.mkString("-"))
```

## `Array`

* Equivalent of Java array
* Can be constructed with predefined lengths
* mutable. Elements can be updated in place. When converted to scala object, the equivalent one is `scala.collection.mutable.WrappedArray`
* interoperable with java arrays
* indexing is fast

```Scala
val array = Array(1,2,3,4)

val threeInts = Array.ofDim[Int](3)
// For primitive types we have default values
threeInts.foreach(println)

val threeStrings = Array.ofDim[String](3)
// For AnyRef types null is the default value
threeStrings.foreach(println)

array(2) = 0
// above call is equivalent to calling update method
// This is a syntactic sugar similar to apply method.
array.update(2, 0)
println(array)

// Here implicit conversion from Array to WrappedArray happen
val numbers: Seq[Int] = array
// This should print WrappedArray
println(numbers)
```

## `Vector`

* Effectively constant indexed read and write: O(log<sub>32</sub>(n))
* Fast element addition on prepend or append
* Implementation as a fixed branch trie (32 element node)
* Good performance on large sizes

```Scala
val emptyVector = Vector.empty
val numbers = emptyVector :+ 1 :+ 2 :+ 3

// update operation returns a new Vector since we are dealing
// with immutable collections
val updatedVector = numbers updated (0, 7)
// On vector I can access member by index, but since vector resides inside
// the immutable namespace, I cannot update the element
println(numbers(3))
```

## Vector vs List

* In case of writes to the collection(updating an element at a particular index), `Vector` is faster than `List` by many times.

```Scala
// vectors vs lists
import scala.util.Random

val maxRuns = 1000
val maxCapacity = 1000000

def getWriteTime(collection: Seq[Int]): Double = {
val r = new Random
val times = for {
    it <- 1 to maxRuns
} yield {
    val currentTime = System.nanoTime()
    collection.updated(r.nextInt(maxCapacity), r.nextInt())
    System.nanoTime() - currentTime
}

times.sum * 1.0 / maxRuns
}

val numbersList = (1 to maxCapacity).toList
val numbersVector = (1 to maxCapacity).toVector

// keeps reference to tail
// updating an element in the middle takes long
println(getWriteTime(numbersList))
// depth of the tree is small
// needs to replace an entire 32-element chunk
println(getWriteTime(numbersVector))
```

---

## References

* [Scala & Functional Programming for Beginners | Rock the JVM](https://www.udemy.com/share/1013xsCUMfd1lVR34=/)
