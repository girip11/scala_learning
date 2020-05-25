# Anonymous classes

* Work for both abstract and non abstract classes as well as traits.

* In case of non abstract classes, we need to pass in the arguments to the constructor.

* Anonymous class should implement all the abstract methods and properties when deriving either from an abstract class or a trait.

```Scala
abstract class Animal {
    def eat: Unit
}

val crunchingCrocodile = new Animal {
    override def eat: Unit = println("crunch")
}

// crunchingCrocodile is an instance of Anonymous class
// that inherits from the abstract class Animal
println(crunchingCrocodile.getClass)

class Person(val name: String, age: Int) {
    def eats: Unit =  println("Eating")
}

// Arguments should be passed to the Person
val john = new Person("John", 25) {
    override def eats: Unit = println("Eating pizza")
}
```

---

## References

* [Scala & Functional Programming for Beginners | Rock the JVM](https://www.udemy.com/share/1013xsCUMfd1lVR34=/)
