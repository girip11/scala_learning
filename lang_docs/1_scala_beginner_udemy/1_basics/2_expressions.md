# Expressions

## Operators

* Arithmetic operators `- + - * / %`
* Bitwise operators - `| & ^ << >> >>>`
* Relational operators - `> < >= <= == !=`
* Assignment operators - `=`. Supports compound assignments like `+= -= *= /=`
* Logical operators - `&& || !`

## Instructions vs expressions

* Instructions - do something (imperative)
* Expressions - evaluates to some value

In scala **every statement is an expression**, which means all statements return some value.

```Scala
val num = 100
// if expression
val isEven = if(num % 2 == 0) true else false
```

* Assignment statement are expressions that return `Unit`.

```scala

var testVar = 0
// This assigns Unit to assignValue
var a, b: Any = null

// This will assign Unit to a and 100 to b
a = b = 100

// This assigns 100 to both the variables.
val assignValue, testVar = 100
// assignValue is of type Unit
println(assignValue)

// We can also assign Unit to a variable by assigning ()
// Empty expression returning Unit
val unitValueVar = ()
```

* Expressions that cause side effects return `Unit`. Example `println`, `while`, assignments

## Loops

* **Loops in scala are discouraged**, since these constructs are imperative programming idiom.

```Scala
var i = 0

while(i < 10) {
    println(i)
    i += 1
}

// for (i <- expr)
for( i <- 1 to 10) {
    println(i)
}

// scope of loop variable ch is the scope of the for loop
for (ch <- "Hello")
    println(ch)
```

* Scala has **no** `break` and `continue` statements. We can use break from the Breaks object. But this approach has performance penalty.

```Scala
import scala.util.control.Breaks._
breakable {
    for (...) {
        if (...) break; // Exits the breakable block
        ...
    }
}
```

**NOTE** - Never use `while` loops in scala

## Code blocks

* Variables defined in a block are visible only within the block.
* Block is also an expression and the value of the block is the **value of the last statement in that block**.

* In scala, similar to python, we can have same variable names in different scopes leading to shadowing rule.

---

## References

* [Scala & Functional Programming for Beginners | Rock the JVM](https://www.udemy.com/share/1013xsCUMfd1lVR34=/)
