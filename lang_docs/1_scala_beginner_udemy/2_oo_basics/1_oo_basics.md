# Object Oriented basics

* Below notes are part of the [Scala lectures on Udemy](https://www.udemy.com/share/1013xsCUMfd1lVR34=/).

## Class and constructor

* Class parameters are accessible within the class only.

```Scala
// name and age are class parameters
// Parameters passed to this class is referred to as primary constructor
class Person(name: String, age: Int) {
// above syntax is same as `class Person(private val name: String, private val age: Int)`
    // This block can contain anything that a normal block can contain
    // Expressions inside this block are evaluated everytime a new instance
    // of this class in instantiated.
    println(s"New instance instantiated: $name and $age")
}

val person = Person("John", 30)
// person.name will raise compilation error since class parameters are not visible outside
```

* Any variable prefixed with `val` or `var` become **class fields** with **public visibility**

```Scala
class Person(val name: String, val age: Int) {
        // This block can contain anything that a normal block can contain
    // Expressions inside this block are evaluated everytime a new instance
    // of this class in instantiated.
    println(s"New instance instantiated: $name and $age")
}

val person = Person("John", 30)
// This works
println(person.name)
println(person.age)
```

## Constructors

* Secondary constructor should call the primary constructor
* Auxilary constructor can call either secondary or the primary constructor
* In most cases, **we can live with just primary constructor with default parameters(similar to default parameters to functions)**

```Scala
// primary constructor
class Person(val name: String, val age: Int) {
    println(s"New instance instantiated: $name and $age")

    // secondary constructor
    def this(name: String) = this(name, 10)

    // auxiliary constructors
    def this(age: Int) = this("John Doe", age)

    def this() = this("John Doe", 10)
}
```

## Methods

* Method overloading allowed in scala.
* Methods should differ in the number of parameters or types of the parameters or both.
* Two methods with same parameter list(or with no parameter list) but with different return types is **not overloading**

```Scala
class Person(val name: String, val age: Int) {
    // This block can contain anything that a normal block can contain
    // Expressions inside this block are evaluated everytime a new instance
    // of this class in instantiated.
    println(s"New instance instantiated: $name and $age")

    // Methods overloading
    def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

    def greet(): Unit = println(s"Hi, I am $name")
}

```
