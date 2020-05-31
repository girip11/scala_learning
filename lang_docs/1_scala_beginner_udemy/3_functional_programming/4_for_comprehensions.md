# Map FlatMap, Filter and For comprehensions

```Scala
val simpleList: List[Int] = List(1,2,3,4)

println(simpleList.head)

println(simpleList.filter(_ %  2 == 0))

println(simpleList.map(_ * 2))

println(simpleList.flatMap(x => List(x, x+1)))
```

* Nested loops

```Scala
// print all combinations of chars and numbers
val chars = List('a', 'b', 'c', 'd')
val numbers = List(1, 2, 3, 4)

val combinations = chars.flatMap(c => numbers.map(n => s"$c$n"))
println(combinations)
```

* With multiple levels of nested loops, flatMap will be used in outer loops and map will be used in the innermost loop.

```Scala
// print all combinations of chars and numbers
val chars = List('a', 'b', 'c', 'd')
val numbers = List(1, 2, 3, 4)
val colors = List("Red", "Green", "Blue")

val combinations = colors.flatMap(color => chars.flatMap(c => numbers.map(n => s"$color$c$n")))
println(combinations)
```

* `.foreach method`

```Scala
val colors = List("Red", "Green", "Blue")

// print elements of the list
colors.foreach(println)
```

## For comprehensions

* This is just a syntactic sugar. Compiler rewrites this to list of maps, flatMaps, withFilter and foreach.

* This is **more readable** compared to the nested `map` and `flatMap`s

```Scala
// print all combinations of chars and numbers
val chars = List('a', 'b', 'c', 'd')
val numbers = List(1, 2, 3, 4)
val colors = List("Red", "Green", "Blue")

// This for comprehension is equivalent to
// for n in numbers {
//      for c in chars {
//          for color i colors {
val combinations = for {
    n <- numbers
    c <- chars
    color <- colors
} yield s"$color-$c$n"

println(combinations)
```

* With **if guards** we can mimic filter functionality.

```Scala
// print all combinations of chars and numbers
val chars = List('a', 'b', 'c', 'd')
val numbers = List(1, 2, 3, 4)
val colors = List("Red", "Green", "Blue")

// This for comprehension is equivalent to
// for n in numbers {
//    if (n % 2 == 0) {
//       for c in chars {
//          for color i colors {
val combinations = for {
    // To rewrite if guard, compiler will look for withFilter
    // method on the iterable object
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
} yield s"$color-$c$n"

println(combinations)
```

* Print a list using for comprehensions

```Scala
// For this to work, the list should have the foreach method implemented
for {
    n <- numbers
} println(n)
```

* Alternate syntax for passing a lambda to a `map` function(applies to filter, flatMap etc)

```Scala
val numbers = List(1, 2, 3, 4)

// All the below syntax are all equivalent
numbers.map { x =>
    x * 2
}

numbers.map(x => x * 2)
numbers.map(_ * 2)
```

---

## References

* [Scala & Functional Programming for Beginners | Rock the JVM](https://www.udemy.com/share/1013xsCUMfd1lVR34=/)
