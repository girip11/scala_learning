# Scala Objects

* Scala does not have class level functionality (**like static in java**).

```Java
class Person {
    public static final int MAX_RETRY = 5
}
```

* Using scala objects, we can achieve the above **static** functionality in Java.

```Scala
// Only instance with name Person
// When we refer to Person as object we have only one.
object Person {
    val MAX_RETRY = 5
}

// assign scala objects to other references.
val constants = Person
println(Person.MAX_RETRY)
```

* Companion objects(If the object is having same name and scope as that of a class) are **singleton instances**
* Can define fields and methods inside the scala objects.
* We can define class with the same name as that of the scala object.
* Instance level functionality is placed inside the class definition.

* Using companion objects we can build factory methods. By implementing `apply()` method on companion objects, we can instantiate classes like function invocation.

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
```

* With scala objects, we can implement singleton pattern very easily.

* Scala companions (object and the class) can access each other's private members.
