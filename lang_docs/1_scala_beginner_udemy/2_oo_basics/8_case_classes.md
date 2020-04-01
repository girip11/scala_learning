# Case classes

> Quick lightweight data structures with little boilerplate.

* **Class parameters become class fields** in case classes
* Sensible default `toString` representation.
* `equals` and `hashcode` implementation **out of the box**. These methods are useful when the instances are used with collections like Set and Map.
* case classes have `copy` method. Creates a new instance from an existing instance.
* Compiler automatically create companion objects for the case classes.
* Default `apply` method to the companion object. So need not use `new` to instantiate case classes.
* Case classes are by **default serializable**.
* Case classes have **extractor patterns**. Very helpful in pattern matching.

```Scala
// name and age become class fields.
// Hence they are accessible from outside
// which is not the case in normal classes.
case class Person(name: String, age: Int)

// case classes have companion objects with apply method
// by default
val personObject = Person

val jane = Person.apply("Jane", 28)
// Better string representation compared to the
// toString of normal classes.
println(jane.toString)

// With default apply method no need for new
val john = Person("John", 28)
println(john.name)

// easy to make a copy of the instance.
val johnSenior = john.copy(age = 50)

// Default equals method that can compare instances.
println(Person("Jacob", 30) == Person("Jacob", 30))

```

## Case Objects

* Very similar to case classes except that **they are themselves the companion objects**.
