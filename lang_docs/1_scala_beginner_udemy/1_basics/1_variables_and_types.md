# Variables and Types

* `;` are optional in scala.

* Mandatory when multiple statements are put in same line

```Scala
val x: Int = 100; println(x);
```

## `var` and `val`

* `val` - to define **immutable** variables.
* `var` - to define mutable variables.

```Scala
// Type information is optional
val x: Int = 100

// scala compiler can inference types
var y = 200
y = 1000
```

* Functional programming paradigm promotes the usage of `val` over `var`

## Basic types

* `Int`, `Short`, `Long` - Integers
* `Float`, `Double` - Floating point numbers
* `String`, `Char` - single quoted
* `Byte`
* `Boolean`
* `Unit` - similar to `void` in Java or `None` in Python

```Scala
val c: Char = 'a'
val str: String = "Hello"

val isEmpty: Boolean = true

```

---

## References

* [Scala & Functional Programming for Beginners | Rock the JVM](https://www.udemy.com/share/1013xsCUMfd1lVR34=/)
