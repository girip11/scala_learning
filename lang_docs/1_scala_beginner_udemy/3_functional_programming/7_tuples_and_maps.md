# Tuples and Maps

## Tuple

* Tuples can group a maximum of 22 elements of different types.
* Tuples don't offer access by index. `tuple(0)` is not allowed.

```Scala
val aTuple: Tuple2[Int, String] = Tuple2[Int, String](2, "Hello, Scala")

// Above syntax can also be written as
val anotherTuple:(Int, String) = (2, "Hello, Scala")

val pairing:(Int, String) = 2 -> "Hello, Scala"

println(anotherTuple._1)

// Create another tuple with provisions to change some/all elements
// Changing all elements of a tuple does not need copying
println(anotherTuple.copy(_2 = "Hello Java"))

// This method is available only for Tuple2
println(anotherTuple.swap)
```

## Maps

```Scala
val phonebook = Map(("Jim", 445), "John" -> 889)
println(phonebook)

phonebook.contains("Jim")
phonebook("Jim")

// Throws NoSuchElemenException
phonebook("Jane")

val defaultPhonebook = phonebook.withDefaultValue(-1)
// this returns -1 when the key is not found
defaultPhonebook("Jane")

// Adding a new pairing to Map
val newPhonebook = phonebook + "Jane" -> 325
println(newPhonebook)

// Functional on maps
// map, flatMap and filter exists on Map but less commonly used
// if two entries "Jim" -> 555 and "JIM" -> 787 are present in the map
// after the mapping these two entries will have the same keys. So, the first
// entry "jim" -> 555 will be updated with the value "jim" -> 787
println(phonebook.map(pairing => pairing._1.toLowercase -> pairing._2)

println(newPhonebook.filterKeys(key => key.startsWith("J"))
println(phonebook.mapValues(value => "0235" + value))

// converts to List[Tuple2[_, _]]
println(newPhonebook.toList)
println(List(("Daniel", 122), ("Jim", 465)).toMap)

// Groupby operation
val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
println(names.groupBy(name => name.charAt(0)))
```

---

## References

* [Scala & Functional Programming for Beginners | Rock the JVM](https://www.udemy.com/share/1013xsCUMfd1lVR34=/)
