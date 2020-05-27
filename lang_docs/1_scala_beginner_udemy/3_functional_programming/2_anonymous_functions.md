# Anonymous functions

* Also called as **lambda**

```Scala
// The below implementations of doubler are equivalent
val doublerFunc = new Function1[Int, Int] {
    override def apply(x: Int): Int = x * 2
}

val doublerFuncSyntatic = new ((Int) => Int) {
    override def apply(x: Int): Int = x * 2
}

// Below syntax are all same
// val doublerAnon: = (x: Int) => x * 2
// val doublerAnon: (Int => Int) = x => x * 2
val doublerAnon: (Int => Int) = (x: Int) => x * 2

println(doublerFunc(2))

println(doublerFuncSyntatic(2))

println(doublerAnon(2))
```

* Multiple parameter types should be placed within paranthesis `(Int, Int) => Int`

* Lambdas should always be **invoked using paranthesis**.

```Scala
val something: () => Int = () => 3

// This prints the toString representation of the lambda
println(something)

// Calls the lambda and prints the value returned
println(something())
```

## Braces with anonymous function

* Enclosing the anonymous function within the curly braces.

```Scala
val stringToInt = { (str: string) =>
    str.toInt
}
```

## Underscores

* Less readable.
* Variable holding reference to the lambda should have type declared.

```Scala
// same as x => x * 2
val doubler: Int => Int = _ * 2


// Adds the two Ints
// Type cannot be omitted for adder
val adder: (Int, Int) => Int = _ + _
```

---

## References

* [Scala & Functional Programming for Beginners | Rock the JVM](https://www.udemy.com/share/1013xsCUMfd1lVR34=/)
