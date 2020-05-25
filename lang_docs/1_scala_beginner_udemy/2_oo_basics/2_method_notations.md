# Method notations

* These notations are syntactic sugars and make the code read more like natural language.

## Infix notation (Operator notation)

* Works with methods that have **only single parameter**
* `object method_name parameter` is equivalent to `object.method_name(parameter)`

```Scala
class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String) = movie == favoriteMovie
}

val john = new Person("John", "The Mask")

// usual way of invocation
println(john.likes("The mask"))

// infix notation
println(john likes "The Mask")
```

* Remember the format of the infix expression `a + b`. In case of infix notation in scala, the method acts like an **operator** between the object and the parameter.

* Method with names `+`, `&` are allowed and makes it a valid method. `1 + 2` is equivalent to `1.+(2)`. `+` is a method on `Int` objects. All operators are methods in scala.

```Scala
class Counter(val value: Int) {
    def +(inc: Int): Counter = {
        if (inc == 0) this
        else new Counter(this.value + inc)
    }
}

val counter = new Counter(10)

// Below expressions are equivalent
println(counter.+(10).value)
println((counter + 10).value)
```

## Prefix Notation

*** This notation is for unary operators**.

* Unary operators are also methods on the object with the prefix **unary_**.

* This notation works **only with the four operators** `- + ~ !`

```Scala
val x = -1

// prefix to distiguish it from the binary
val y  = 1.unary_-
```

## Postfix Notation

* Functions with no parameters can be used as the operator in the postfix notation. `object method_name`.

```Scala
class Person(val name: String, alive: Boolean) {
    def isAlive: Boolean = this.alive
}

val john = new Person("John", true)
println(john.isAlive)
// Note the space separating the object and the method.
println(john isAlive)
```

## `apply()`

* When implemented on object, the object becomes callable.

* If the `apply()` accepts parameters, then we can call the object with those parameters.

```Scala
class Person(val name: String, profession: String) {
    // Overloading of apply is allowed
    def apply(): String = s"I am $name. I am a $profession"
}

val jane = new Person("Jane", "Writer")

// Below statements are equivalent
println(jane.apply())

// To be called like this apply method without parameters should be present
println(jane())
```

* If we implement the `apply()` method on class(Class's companion object), then we can create use this method as the factory method to create its instances.

---

## References

* [Scala & Functional Programming for Beginners | Rock the JVM](https://www.udemy.com/share/1013xsCUMfd1lVR34=/)
