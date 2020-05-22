# Scala Objects

* Scala does not have class level functionality **like static in java**. But it provides class level functionality in the form of scala objects(companions).

```Java
class RequestHandler {
    public static final int MAX_RETRY = 5
}
```

* Using scala objects, we can achieve the above **static** functionality in Java.

```Scala
// Only instance with name RequestHandler
// When we refer to RequestHandler as object we have only one.
object RequestHandler {
    val MAX_RETRY = 5
}

// assign scala objects to other references.
val constants = RequestHandler
println(RequestHandler.MAX_RETRY)
```

* Companion objects are scala objects that are having the same name and scope as that of a scala class. Companion objects are **singleton instances** that can be referred using the same name as that of the class.

* Can define fields and methods inside the scala objects.

* We can define class with the same name as that of the scala object in the same scala source file.

* **Instance level functionality** is placed inside the class definition.

* Using companion objects we can build **factory methods**. By implementing `apply()` method on companion objects, we can instantiate classes like a function call.

```Scala
object Person {
    def apply(name: String): Person = {
        new Person(name)
    }
}

class Person(val name: String) {

}

// Note new keyword is not used
val john = Person("John")
println(john.name)
```

* Scala applications usually have a **scala object** with a `main` method as its entrypoint.

```Scala
object MyApp {
    def main(args: Array[String]): Unit = {
        println("Hello World")
    }
}

// OR
// inherit the main method from the App scala object
object MyApp extends App {
    println("Hello World")
}
```

* With scala objects, we can implement **singleton pattern** very easily.

* Scala companions (object and the class) can access **each other's private members**.

* We can also define companion objects for traits.
