# Anonymous classes

* Work for both abstract and non abstract classes

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

// Arguments should be passes to the Person
val john = new Person("John", 25) {
    override def eats: Unit = println("Eating pizza")
}
```

* Works for traits as well. In this case, all the abstract fields and methods in the trait should be implemented by the anonymous class.
