# Functions

* JVM - objects are first class elements.

* Function as first class elements.  Objects can be made to behave like function with apply method(objects become callable).

* Higher order functions receive other functions as parameters or return a function as a result.

```Scala
trait MyFunction[A, B] {
    def apply(element: A): B
}

val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
}

// object invoked like a function
println(doubler(2))
```

* Scala supports function types out of the box. `Function1[Param, return_type]` accepts single parameter. Scala supports up to function trait `Function22` that accepts 22 parameters.
`Function2[A,B,R] === (A,B) => R`

```Scala
val stringToInt = new Function1[String, Int] {
    override def apply(value: String): Int = value.toInt
}

val stringToIntSyntactic = new ((String) => Int) {
    override def apply(value: String): Int = value.toInt
}

println(stringToInt("10"))
```

* All scala functions are objects that derive from traits `Function1 - Function22` types

* `Function1[Int, String, Int]` can also be syntactically expressed as `(Int, String) => Int`

* Curried functions - Functions that accept parameters and return function as result.

```Scala
// process function is called curried function
// The function returned is called a closure.
val process: (Int => (Int => Int)) = new (Int => (Int => Int)) {
    override def apply(x: Int): Int => Int = new (Int => Int) {
        override def apply(y: Int): Int = x + y
    }
}

val addTwo = process(2)
println(addTwo(5))

// Function currying
// Passing multiple parameter set
process(2)(3)
```

---

## References

* [Scala & Functional Programming for Beginners | Rock the JVM](https://www.udemy.com/share/1013xsCUMfd1lVR34=/)
