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
val assignValue = (testVar = 100)
// assignValue is of type Unit
println(assignValue)
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
```

**NOTE** - Never use `while` loops in scala

## Code blocks

* Variables defined in a block are visible only within the block.
* Block is also an expression and the value of the block is the **value of the last statement in that block**.
