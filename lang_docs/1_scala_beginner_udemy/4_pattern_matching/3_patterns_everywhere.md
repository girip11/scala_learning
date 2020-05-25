# Patterns everywhere

* In `try..catch`, catch block does pattern matching on exception instances.

* Generators (for comprehensions) in scala use pattern matching

```Scala
val evenOnes = for {
    x <- list if x % 2 == 0
    } yield 10 * x
}
```

* Tuple unpacking also uses pattern matching

```Scala
val (a,b,c) = (1,2,3)
println(a)
println(b)
println(c)
```

* List unpacking also uses pattern matching

```Scala
val head :: tail = List(1,2,3,4)
println(head)
println(tail)
```

* Partial functions also use pattern matching. Partial functions are covered in the scala advanced course.

```Scala
val list = List(1,2,3,4)

// We have passes partial functions to the list.map method
val mappedList = list.map {
    case v if v % 2 == 0 => s"$v is even"
    case 1 => "One"
    case _ => "something else"
}
```

---

## References

* [Scala & Functional Programming for Beginners | Rock the JVM](https://www.udemy.com/share/1013xsCUMfd1lVR34=/)
