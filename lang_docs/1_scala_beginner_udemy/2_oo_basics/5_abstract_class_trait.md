# Abstract Classes and Traits

## Abstract classes

* `abstract` class - meant to be extended later.

* `override` keyword is optional for abstract fields and methods.

```Scala
abstract class Animal {
    val CreatureType: String
    def eat: Unit
}
```

## Traits

* By default fields and methods in a trait are public and abstract
* But traits can contain non abstract implementations as well. Helps in mixins.
* Mixin as many traits as we want

```Scala
abstract class Animal {
    // concrete field in abstract class.
    val CreatureType: String = "wild"
    def eat: Unit
}

trait Carnivore {
    // concrete fields in traits
    val PreferredMeal: String = "Fresh Meat"
    def eat(animal: Animal): Unit
}


class Crocodile extends Animal with Carnivore {
    override val CreatureType = "Crocodile"
    override def eat: Unit = println("nomnom")
    override def eat(animal: Animal): Unit = println(s"I am a $CreatureType, I am eating ${animal.CreatureType}")

}
```

## Abstract class vs Trait

* Trait cannot have a constructor
* Can extend only one class but mixin many traits
* Traits represent a type of behavior.

## Type hierarchy

* `scala.Any` - Root
* `scala.AnyRef` - Base of all classes
* `scala.Null` - inherits from everything that is an AnyRef.
* `scala.AnyVal` - inherits from `Any`. Base class of primitive types
* `scala.Nothing` - subtype of every type in scala(including `scala.Null`).Represents nothingness
