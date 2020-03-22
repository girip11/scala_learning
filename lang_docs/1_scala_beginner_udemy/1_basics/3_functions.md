# Functions

* Functions with more than 1 statement needs to enclose the statements inside a block.

```Scala
object MyApp extends App {

    // Functions with single expression need no braces
    def printHelloWorld(): Unit = print("Hello World")

    def sayHello(name: String): String = {
        return "Hello " + name
    }

    println(sayHello("Girish"))

    // functions without arguments can be called just using the name
    println(printHelloWorld)
}
```

* In functional programming, use recursive function(particularly **tail recursion**) when you need a loop.

* Return type of the function is optional and compiler infers the return type. **Return type is not optional for a recursive function**. Good practice is to always mention the return type.

* Functions with no return value have the return type as `Unit`

* Functions with no parameters can be called without the paranthesis while invoking.

* Functions can have inner/nested functions inside them.

```Scala
def outerFunc(arg: String): String = {
    def innerFunc(dumpParameters: Boolean ): Unit = {
        if (dumpParameters) {
            println("Inner function can access the variables in the scope of the outer function" + arg)
        }
    }
    innerFunc(true)
    arg
}
```
