# Generics

* Makes a class, trait, methods etc to work for any type.

## Generic classes, traits

```Scala
// This is a generic class
class MyList[A] {
    def empty[A]: MyList[A] = ???
}

val listOfIntegers = new MyList[Int]
val listOfStrings = new MyList[Int]

// class with multiple generic types
class MyMap[Key, Value]

// Traits can also be type parameterized
trait MyList[A] {
    def empty[A]: MyList[A]
}
```

* Generics cannot be applied to scala `object`. But methods inside the scala object can have type parameters.

```Scala
object MyList {
    def empty[A]: MyList[A] = ???
}

val emptyList = MyList.empty[Int]
```

## Covariance

* Can substitute parent with child type.

```Scala
class CovariantList[+A]

val animalList: CovariantList[Animal] = new CovariantList[Cat]
```

## Invariance

* Cannot substitute one type for another.

```Scala
class InvariantList[A]

// Below definition is not allowed with invariant list
val invariantList: InvariantList[Animal] = new InvariantList[Cat]
```

## Contravariance

* Opposite of covariance

```Scala
class Trainer[-A]

val trainer: Trainer[Cat] = new Trainer[Animal]
```

## Bounded types

Can impose constraints like the type parameter to be a subclass or superclass of a specific type.

```Scala
// can hold anything that is a subtype of Animal
class Cage[A <: Animal](animal: A)

// This can accept only types that are superclass of Animal
class Cage[A >: Animal]
```

* Covariance and bounded types help store instances of subclasses in a container object.

```Scala
class MyList[+A] {
    def add[B >: A](element: B): MyList[B] = ???
}

// This way we can add cats
// and dogs to the MyList since both are Animals
```

More on generics and type system is covered in the advanced course.

---

## References

* [Scala & Functional Programming for Beginners | Rock the JVM](https://www.udemy.com/share/1013xsCUMfd1lVR34=/)
