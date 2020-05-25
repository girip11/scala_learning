# Call by name and call by value

## Call by value

* In call by value, the value of the expression is passed.

```Scala
import scala.util.Random

def callByValue(n: Int): Unit = {
    println("Value: " + n)
    println("Value: " + n)
}

// Here the expression Random.nextInt is evaluated first
// and the value is passed to the function
callByValue(Random.nextInt(10))
```

## Call by name

* In call by name, the expression itself gets passed as the parameter to the function.

```Scala
import scala.util.Random

def callByName(randomGen: => Int): Unit = {
    println("callByName: " + randomGen)
    println("callByName: " + randomGen)
}

// Here the expression Random.nextInt is passed to the
// function as the parameter. Everytime the argument is used
// the expression is evaluated.
callByName(Random.nextInt(10))
```

* **Delays the evaluation of the expression** till its first use.

---

## References

* [Scala & Functional Programming for Beginners | Rock the JVM](https://www.udemy.com/share/1013xsCUMfd1lVR34=/)
* [Call by name tricks](https://rockthejvm.com/blog/207478/3-tricks-cbn)
