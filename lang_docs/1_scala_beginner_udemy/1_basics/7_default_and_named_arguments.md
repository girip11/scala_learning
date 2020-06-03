# Default and named arguments

```Scala
def factorial(n: Int, acc: Int = 1): Int = {
    if (n <= 1) acc
    else factorial(n-1, n * acc)
}

factorial(5)
```

* Default value can also be obtained from invoking another function.

```Scala
def getDefaultValueForAcc(): Int = 1

def factorial(n: Int, acc: Int = getDefaultValueForAcc): Int = {
    if (n <= 1) acc
    else factorial(n-1, n * acc)
}

factorial(5)
```

* If the function returns a mutable data structure as the value for the default argument, unlike python each function call will get the default value after calling the function. In python, the default value returned by the function is called and never called again

```Scala
def getDefaultValueForAcc(): ArrayBuffer[String] = {
  println(" I am called" )
  ArrayBuffer[String] ()
}

def testFunction(value: ArrayBuffer[String] = getDefaultValueForAcc): Unit = {
  value += "John"
  println(value)
}

// new array buffer will be returned as the default parameter value
// everytime the function is called.
testFunction()
testFunction()
```

* Default arguments can be placed anywhere in the parameter list.
* But if the default parameters occur before positional parameters, then to invoke the function we make use of **named arguments** to skip passing values to default parameters

* In method invocation, positional arguments should be placed before the named arguments.

```Scala
def getUrl(protocol: String = "https", host: String, port: Int = 80): String = {
    "%s://%s:%s".format(protocol, host, port)
}

// using named arguments
// Without named arguments we cannot invoke the function
// with just passing value to the host parameter
getUrl(host="wikipedia.com")

// No named arguments required
getUrl("http", "wikipedia.com")

// Mix of named and positional arguments
// During method call, the positional arguments should always come
// before the named arguments
getUrl("http", host = "wikipedia.com")

// Using all named arguments
// Order of arguments doesnot matter when all the
// arguments are named
getUrl(port= 8080, protocol = "http", host = "wikipedia.com")
```

Hence it is a good practice to place the default arguments at the last following the required arguments

## Variable arguments

```Scala
// numbers is of type Seq
def sum(numbers: Int*): Int = {
    var s = 0
    for (num <- numbers) s += num
    s
}
```

* To unpack an iterable to argument list use the `: _*` syntax

```Scala
// numbers is of type Seq
def recursiveSum(numbers: Int*): Int = {
    if (numbers.length == 0) 0
    else numbers.head + recursiveSum(numbers.tail: _*)
}

recursiveSum(1 to 10: _*)
```

---

## References

* [Scala & Functional Programming for Beginners | Rock the JVM](https://www.udemy.com/share/1013xsCUMfd1lVR34=/)
