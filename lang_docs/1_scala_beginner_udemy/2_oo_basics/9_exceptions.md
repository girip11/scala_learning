# Exceptions

* throwable classes extend `Throwable`
* `Exception` and `Error` - are major `Throwable` subtypes in scala
* Exception - something wrong with the program ex: `NullPointerException`
* Error - Outside the program like `StackOverflowError`

## Raising/throwing exceptions

* `throw new Exception` - this expression returns `Nothing`

## Exception handling

* `try` and `catch` clauses are used for handling exceptions with optional `finally` block

* `try catch finally` is one expression in scala. Return value could be from either try block or from catch block. `finally` does not influence return value of `try...catch` expression.

```Scala
import scala.util.Random
def handleException: Unit = {
    try {
        println("Try block")
        val randomValue = Random.nextInt(100)
        if (randomValue > 50) {
            throw new RuntimeException("Greater than 50")
        }
        println("Safely reached end of try block")
    } catch {
        case e: RuntimeException => println(s"RuntimeException caught: ${e}")
    } finally {
        println("Finally: Executed irrespective of exception thrown or not")
    }
}
```

## Custom Exception

* Custom exception classes extend the `Exception` class

```Scala
class MyException extends Exception
throw new MyException
```

## Exercises

### Raise OutOfMemoryError

```Scala
// Raises OutOfMemoryError(OOM)
val myarray = Array.ofDim(Int.MaxValue
```

## Raise StackoverflowError

```Scala
// This function never returns
def createSO: Int = 1 + createSO

createSO
```
