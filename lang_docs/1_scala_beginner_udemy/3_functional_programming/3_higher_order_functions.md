# Higher order functions

* A function that takes another function as parameter or returns a function are called higher order functions(HOF)

```Scala
def nTimes(f: Int => Int, n: Int, x: Int) : Int = {
    if (n<=0) x
    else nTimes(f, n-1, f(x))
}

val result = nTimes((x: Int) => x + 1, 10, 1)
println(result)
```

* A different implementation of `nTimes`

```Scala
def nTimesBetter(f: Int => Int, n: Int) : (Int => Int) = {
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n-1)(f(x))
}

println(nTimesBetter((x: Int) => x + 1, 10)(1))
```

* Using higher order functions we can create curried functions. Currying deals with functions with multiple parameter lists.

```Scala
// => is right associative
val adder: (Int => (Int => Int)) = (inc: Int) => (value: Int) => value + inc
println(adder(3)(10))
```

* Functions with multiple parameter lists can be created in another way.(Another form of curried functions)

```Scala
def curriedFormatter(template: String)(value: Double): String = template.format(value)

// providing a type for standardFormatter is mandatory
// Without explicitly defining type, the compiler will think of
// RHS expression as a function call but with less parameters.
// By entering the type compiler understands that the return type is
// a function that can accept rest of the parameter lists
val standardFormatter: (Double => String) = curriedFormatter("%4.2f")

// This syntax also works but less readable
// By providing _ for the other parameter list, compiler implicitly
// understands the LHS type is a function type.
val standardFormatter2 = curriedFormatter("%4.2f")(_)

val preciseFormatter: (Double => String) = curriedFormatter("%10.8f")

println(standardFormatter(Math.PI))
println(standardFormatter2(Math.PI))

println(preciseFormatter(Math.PI))
```

* In the above syntax, we can create special functions with some of the parameter list already populated.

---

## References

* [Scala & Functional Programming for Beginners | Rock the JVM](https://www.udemy.com/share/1013xsCUMfd1lVR34=/)
