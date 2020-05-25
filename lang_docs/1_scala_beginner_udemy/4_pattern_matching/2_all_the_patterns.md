# All the Patterns

* Scala object can also be used in pattern matching.

## Match anything

* We can use either `_` or `someVariable`

```Scala
val value: Any = 1

value match {
    // This can match any value(wildcard)
    // similar to _ but the difference is when the variable
    // is used we can use the value in the case statement
    case someValue => println(s"This is $something")
}
```

## Tuple matching

* Can also match nested tuples.

```Scala
val tuple = (1, 2)

// This is just an example that shows
// various ways using which we can match tuples
tuple match {
    case (1, 2) => println("matched (1, 2)")
    case (a, 2) => println(s"matched ($a, 2)")
    case (1, b) => println(s"matched (1, $b)")
    case (a, b) => println(s"matched ($a, $b)")
    case _ -> println("Something other than a 2-tuple")
}
```

## List matching

```Scala
val list = List(1,2,3,4)

list match {
    // Below syntax of case statements show various ways with
    // which we can pattern match the above list
    case List(1, _, _, _) => println("This will match")
    case List(1, _*) => println("Matching using varargs like syntax")
    case 1 :: List(_) => println("This is infix notation")
    case List(1,2,3) :+ 4 => println(" This is another form of infix notation")
}
```

## Matching using types

```Scala
List(1,2) match {
    // matching using explicit types
    case list: List[Int] => println(s"List of ints: $list")
    case _ => println("some other list")
}
```

## Name binding

* Works inside nested patterns too.

```Scala
val nameBindingMatch = MyConcreteList(1, EmptyList) match {
    case nonEmptyList @ MyConcreteList(_, _) => println("Example of name binding match")
    case MyConcreteList(1, nest @ MyConcreteList(_, _)) => println("Example of nested name binding")
    case _ => println("default case")
}
```

## Matching multiple patterns

```Scala
val someValue: Any = 3

someValue match {
    case 3 | "Scala" => println("Integer value 3 or String \"Scala\"")
    case _ => println("something else")
}
```

## Corner case

* JVM does **type erasure** to be backward compatible with older versions of JVM(since generics was introduces in Java 5). This will cause issues when we use explicit types in various cases.

```Scala
val numbers = List(1,2,3,4)

// This prints list of Strings, because after type erasure both case \
// look like ints: List and strings: List
numbers match {
    // after compilation case pattern becomes strings: List
    case strings: List[String] => printlns("List of Strings")
    // after compilation case pattern becomes ints: List
    case ints: List[Int] => println("List of Integers")
    case _ => println("")
}
```

---

## References

* [Scala & Functional Programming for Beginners | Rock the JVM](https://www.udemy.com/share/1013xsCUMfd1lVR34=/)
