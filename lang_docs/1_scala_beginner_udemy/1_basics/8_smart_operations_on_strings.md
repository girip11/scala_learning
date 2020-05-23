# Smart operations on Strings

```Scala
val sampleStr: String = "Hello World"

println(sampleStr.charAt(0))

// [startindex, endIndex)
// mathematically [3, 7) exclusive of the end index
println(sampleStr.substring(3, 7))

println(sampleStr.split(" "))

println(sampleStr.startsWith("Hell"))

println(sampleStr.toLowerCase())
println(sampleStr.toUpperCase())

println(sampleStr.length)

println(sampleStr.replace("ll", "k"))

// reverses the string
println(sampleStr.reverse)

// First n chars from the string
println(sampleStr.take(5))

val count = "100".toInt
println(count)
```

## String interpolation

* **s-interpolators** - `$` and `${}`

```Scala
val name: String = "John Doe"
val age: Int = 30

println(s"My name is $name and I am $age years old")
```

* Expressions are enclosed within `${}`

```Scala
println(s"value of 5 * 2 is ${5 * 2}")
```

* **f-interpolators** using `printf` like format. They can also check for type correctness

```Scala
val pi: Float = 22/7.0f

println(f"value of pi is $pi%2.3f")
```

* **raw interpolators** - no escaping on string literals, whereas the contents inside the `$` expressions are escaped.

```Scala
// No escaping
println(raw"This is a \n newline character")

val msg: String = "This is a \n newline character"
// This will be escaped
println(raw"$msg")
```

---

## References

* [Scala & Functional Programming for Beginners | Rock the JVM](https://www.udemy.com/share/1013xsCUMfd1lVR34=/)
