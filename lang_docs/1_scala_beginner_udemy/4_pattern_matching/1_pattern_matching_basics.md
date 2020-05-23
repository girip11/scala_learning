# Pattern matching Introduction

* Similar to switch case in languages like Java. But much more powerful.

* Return value of the match expression is the type that is the lowest common ancestor of the return types of all the case expressions.

* For instance, if match has two case expressions, one returns `Int` and the other returns `String`, then `Any` is the return type of the value returned by the match expression.

* Matches **the first case expression** in the order that is declared and then returns the value from that case statement.

* `scala.MatchError` is raised when none of the patterns match the input value.

## Syntax

* The pattern to match can be a literal value(ex: Integer, String literal), a wildcard like `_` or `variable`, case class constructor pattern.
* Along with patterns `if` guards can also be used for conditional matching.

```Scala
variable match {
    case 1 => // block matches variable with value 1
    case "Scala" => // block matches variable with value Scala
    case _ => // this matches anything. Called as wildcard
}
```

## Match a value

```Scala
import scala.util.Random

val rand = new Random
randomValue = rand.nextInt(10)

// We can use literal values for matching
val matchedValue = randomValue match {
    case 1 => "One"
    case 2 => "Two"
    case _ => "Wildcard matches the rest."
}

println(matchedValue)
```

## Match a case class

* Case classes come up extractor patterns so that they can be used in pattern matching. Normal classes to be used in the pattern matching, extractor patterns have to be explicitly implemented by the user.

* Pattern should match the constructor of the case class.
* Values can be used to match the constructor parameters
* If variables are used in the constructor pattern, then the variables can be used inside the matched case statement.
* Variables can also be nested if the constructor accepts another object.

```Scala
case class Person(name: String, age: Int)

val john = Person("John", 25)
val jane = Person("Jane", 25)

def getPersonDescription(person: Person): String = {
    person match {
        // case pattern with if guards
        case Person(n, a) if n == "John" => s"Matched John. His age is $a"
        // Matches all instances of Person whose name is Mary
        case Person("Mary", a) => s"Matched Mary and her age is $a"
        // match any instance of person case class
        case Person(n, a) => s"Matched person $n with age $a"
        case _ => "Something else"
    }
}

println(getPersonDescription(john))
println(getPersonDescription(jane))
println(getPersonDescription(Person("Mary", 25)))
```

* We can match multiple case classes in the same case pattern using `|`

```Scala
trait Person {
    val name: String
    val age: Int
}
case class Student(name: String, age: Int) extends Person
case class Teacher(name: String, age: Int) extends Person

def isJohn(person: Person): Boolean = {
    person match {
        // Whether the person is student or Teacher we want to see
        // if the person is John. Since we dont care about the age
        // we used underscore to capture values that are insignificant to us
        case Student("John", _) | Teacher("John", _) => true
        // we can also use a variable value like a wildcard instead of _
        // But this variable can be used in the case statement.
        case somebody => {
            println(s"This is $somebody")
            false
        }
    }
}

println(isJohn(Teacher("Mary", 25)))
println(isJohn(Student("John", 25)))
```

## On sealed classes

* On sealed classes, we get pattern matching warning when all the cases are not covered.

* Compiler knows the inheritance due to the nature of the sealed class(can be inherited only in the same source file).

```Scala
sealed class Animal
case class Dog(breed: String) extends Animal
case class Parrot(greeting: String) extends Animal

val animal: Animal = Dog("Labrador")

// on compilation will raise a warning
// "match may not be exhaustive"
animal match {
    case Dog(breed) => println(s"Found dog with breed: $breed")
}
```

---

## References

* [Scala & Functional Programming for Beginners | Rock the JVM](https://www.udemy.com/share/1013xsCUMfd1lVR34=/)
