package org.scala.sftiPlayground.chapter13

// scalastyle:off regex.println
object chapter13Tryouts extends App {

  // iterables -> sequences, sets and maps

  // sequences(Seq) - indexed seq(IndexedSeq) and lists
  // IndexedSeq - Vector and Range are immutable while ArrayBuffer is mutable
  // lists - List(immutable) while ListBuffer is mutable
  // Its common to use recursion to traverse through lists
  // ListBuffer - Inserting or removing at the end is very efficient on the lists
  // while inserting or removing in the middle is very inefficient

  //============================================================================
  //Sets - contains distinct elements only
  //HashSet is present under mutable and immutable packages
  // LinkedHashSet - only part of mutable package. Retains the insertion
  // order of the elements
  // SortedSet - present under immutable and mutable. We can
  // iterate over elements in sorted order.
  // BitSet - present under mutable and immutable packages. stores bits of non negative integer

  // contains, subsetOf, union, intersect, diff are some important operations with Set.

  // Mapping a function
  // map, flatMap, foreach, filter, transform, collect, groupBy

  // Binary operations
  // reduceLeft, reduceRight, foldLeft, foldRight, scanLeft, scanRight

  //Zipping
  //zip, zipAll and zipWithIndex

  // Iterators
  // All iterables have an iterator method that returns an Iterator
  // They return/compute elements on demand. Memory efficient compared to Lists
  // they can be iterated exactly once
  // Many methods available on Iterable is also available on Iterator

  // Iterators vs  Streams
  // Both compute the values lazily(on demand)
  // While iterators are mutable(modified every time we call next)
  // while streams are immutable. also we can revisit the already
  // computed elements again
  // Iterators can be converted to stream using the `toStream` method

  val itr = scala.io.Source.fromString("Hello world")

  val stream:Stream[Char] = itr.toStream

  // return another stream advanced to the 3th element
  println(stream(2))

  // stores the already computed elements
  println(stream)

  //revisit computed elements
  println(stream(0))

  //This will not trigger the computation
  // This will just return another stream containing max of 5 elements only
  // starting from the beginning of the source stream.
  val anotherSubStream = stream.take(5)
  println(anotherSubStream) // contains [H, e, l, l, o]

  // Force method triggers the computation. Never invoke force on infinite streams
  println(anotherSubStream.force)


  //============================================================================
  // Lazy views
  // views are extremely lazy, not even first element is computed.
  // Views also don't stored already computed values.
  // Computation always starts from the beginning of the sequence.
  // force method triggers computation of the entire sequence

  // calling view method on collections returns lazy views
  val lazySquares = (1 to 10000).view
    .map(x => x * x)

  // This returns a Vector
  // NOTE: The apply method forces evaluation of the entire view. Instead of
  //calling lazyView(i), call lazyView.take(i).last.
  println(lazySquares.take(20).force)

  // Computation starts from beginning again for every take
  println(lazySquares.take(5).force)

  // Notice: force is not the only method that triggers computation
  // methods like foreach, mkString etc also trigger computation
  // Something similar to transformations and actions in spark
  // transformations are evaluated lazily.
  println(lazySquares.take(5).foreach(println))
  println(lazySquares.take(5).count(_ % 2 == 0)) // another action

  //==================================================================================
  // Java and scala collections interoperability
  // References:
  // 1. https://www.scala-lang.org/api/2.12.1/scala/collection/JavaConverters$.html
  // 2. https://alvinalexander.com/scala/how-to-go-from-java-collections-convert-in-scala-interact/

  // scala.collection.JavaConversions is deprecated in scala 2.12
  // We should be using scala.collection.JavaConverters
  import scala.collection.JavaConverters._
  val jList = java.util.Arrays.asList(1, 2, 3)
  val sBuffer = asScalaBuffer(jList)

  // java set to scala set
  val jSet = new java.util.HashSet[Int]()
  jSet.addAll(jList)
  val sSet = asScalaSet(jSet)

  // From scala 2.13 onwards the
  // correct approach is to now use the scala.jdk.CollectionConverters object
  // to convert from Java collections classes to Scala collections classes.
  // Also, use the scala.jdk.javaapi.CollectionConverters object to convert
  // from Scala collections classes to Java collections classes.


  //==========================================================================
  // parallel collections
  // par method on collections returns parallel collections which are extend
  // ParSeq, ParSet and ParMap. These are not subtypes of Seq, Set and Map traits
  for {
    i <- (1 to 100).par
  } println(i)

  // to convert parallel collections to normal collections
  // we can use seq, toSeq, toSet, toStream methods
  println((1 to 10).par.seq)

  // normal collection
  println("hello".foldLeft(Set[Char]())(_ + _))

  // same using parallel collection
  // first parameter accepts a default value for accumulator
  // In the next parameter set, first parameter is the fold operation
  // second parameter is to say how to combine the results from parallel operations
  // In this case combine sets formed from each subset
  println("hello".par.aggregate(Set[Char]())(_ + _, _ ++ _))

  //parallel sum using aggregate, since sum op is associative
  // we can do the sum using foldLeft as well as aggregate
  println((1 to 100).par.foldLeft(0)(_ + _))
  // Since complex folds using datastructures like set, map cannot be done with parallel
  // foldLeft, we go for aggregate since it provides extra logic on how to combine the
  // separately collected results.
  println((1 to 100).par.aggregate(0)(_ + _, _ + _))
}
