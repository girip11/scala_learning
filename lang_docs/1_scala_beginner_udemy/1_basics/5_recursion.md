# Recursion

* `StackOverflowError` - recursion stack it too deep

```Scala
// This is not a tail recursion since the last expression does not
// contains only the recursive call. Instead it is an arithmetic expression
// containing the recursive call.
def factorial(n: Int): Int = {
    if (n<=1) 1
    else n* factorial(n-1)
}
```

* Tail recursion - use recursive call as the last expression. Stack frames are reused in case of tail recursion and hence **avoiding** stackoverflow error.

```Scala
import scala.annotation.tailrec
// this annotation can help in verifying if the function
// is tail recursive with the help of compiler
def factorial(n: Int): BigInt = {
    @tailrec
    def factorialHelper(n: Int, acc: BigInt): BigInt = {
        if (n<=1) acc
        else factorialHelper(n-1, n * acc)
    }

    factorialHelper(n, 1)
}
```

* To know if a recursive function is a tail recursive function, we can add `@tailrec` annotation to the function. During compilation, compiler will raise error if the function is not tail recursive.

* In functional programming, use recursive function(particularly **tail recursion**) when you need a loop.

* Use as many accumulators as needed in a function to make it tail recursive.

* In nested functions that are tail recursive we can refer to the parameters and variables from the outer/enclosing function.

```Scala
// repeat a string n times
import scala.annotation.tailrec

def repeatString(str: String, n: Int): String = {
    @tailrec
    def repeatStringHelper(count: Int, acc: String): String ={
        if (count <= 1) acc
        else repeatStringHelper(count - 1, acc + str)
    }

    repeatStringHelper(n, str)
}
```

```Scala
import scala.annotation.tailrec

def isPrime(n: Int): Boolean = {
    // This is without using an accumulator
    @tailrec
    def isPrimeHelper(div: Int): Int = {
        if (n % div == 0) div
        else isPrimeHelper(div-1)
    }

   (n <=3 || isPrimeHelper(n/2) == 1)
}

// Version2 with accumulator
def isPrime(n: Int): Boolean = {
    // This is without using an accumulator
    @tailrec
    def isPrimeHelper(div: Int, isStillPrime: Boolean): Boolean = {
        if (!isStillPrime) false
        else if (div <= 1) true
        else isPrimeHelper(div-1, (n % div != 0 && isStillPrime))
    }

   (n <=3 || isPrimeHelper(n/2, true))
}
```

* Using multiple accumulators in the tail recursive function.
* Number of accumulators = Number of recursive calls

```Scala
import scala.annotations.tailrec

def fibonacci(n: Int): Int = {
    @tailrec
    def fibHelper(count: Int, cur: Int, prev: Int): Int = {
        // Notice the counter need not always be decremented
        // as seen in the above two examples.
        if(count >= n) cur
        else fibHelper(count + 1, (cur + prev), cur)
    }

    fibHelper(2, 1, 1)
}
```
