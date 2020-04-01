# Inheritance

* Scala supports single inheritance like Java.

* `extends` keyword for inheriting from a base class.

* `private`, `protected` access modifiers. Cannot be called from outside the object.

* `private` methods and attributes of a class cannot be accessed directly from inside the methods of the subclasses.

* `protected` methods can be called from subclasses as well as overriden by subclasses.

```Scala
class Animal {
    private def eat: Unit = println("numnum")

    protected def eatFood: Unit = this.eat
}

class Cat extends Animal {
    def crunch: Unit = {
        // we cannot call this.eat from subclass
        this.eatFood
    }
}

val c = new Cat
c.crunch
```

## Constructors

* Subclass should ensure calling into any of its super class constructors(primary,secondary or auxillary)

```Scala
class Person(val name: String, val age: Int) {
    def this(name: String): Unit = this(name, 25)
}

class Student(val name: String, val age: Int, val id: String) extends Person(name, age)

// calling the secondary constructor
class Employee(val name: String, val age: Int, val id: String) extends Person(name)
```

## Override

* `override` keyword before the method/attribute that is being overridden.

* Attributes/fields can be overriden in the constructor itself.

* Overridden methods and fields are called on the actual underlying type of object though the reference may be of its parent type.

```Scala
class Animal {
    val AnimalType: String = "Animal"
    def makeSound: Unit = println("Noise")
}

class Cat extends Animal {
    override val AnimalType: String = "Cat"
    override def makeSound: Unit = println("Meow")
}

class Dog(override val AnimalType: String = "Dog") extends Animal {
    override def makeSound: Unit = println("BowBow")
}

val cat: Animal = new Cat
// calls the makeSound method on the Cat object
// though the type of the reference is Animal
// This is polymorphism
cat.makeSound

val dog: Animal = new Dog("Husky")
dog.makeSound
```

## `super` keyword

* Call methods of parent class

```Scala
class Parent {
    def doSomething: Unit = println("From Parent: Doing something")
}

class Child extends Parent {
    override def doSomething: Unit = {
        super.doSomething
        println("From Child: Doing something")
    }
}
```

## Preventing overrides

* `final` - prevents overriding a method or field.
* When `final` is used on a class, prevents the class from being subclassed.

* `sealed` - inheritance allowed in the same file but not by types defined on different file.
