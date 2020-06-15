package org.scala.sftiPlayground

// Learning chapter8 from scala for the impatient book

object Chapter8 extends App {
// final keyword to prevent a class from being subclasses
//  and a field from being overridden

  class SimplePerson
  // Inherit from another class using the `extends` keyword
  class Employee extends SimplePerson {
    var salary: Double = 0.0
  }

  //===============================================================================
  // Use `override` modifier when overriding a method that isn't abstract
  class AnotherSimplePerson {
    var name: String = ""
    override def toString: String = "My name is $name"
  }

  // `super` keyword is used to call method with same name in the superclass
  class AnotherEmployee extends AnotherSimplePerson {
    var salary: Double = 0.0
    override def toString: String = s"${super.toString}. My salary is $salary"
  }
  //===============================================================================


  //===============================================================================
  def typecasts(person: AnotherSimplePerson): Unit = {
  // isInstanceOf returns true if the object is an instance of either
  // AnotherEmployee or its subclass
  // Basically this method checks whether the object confirms to "is a"
  // relation with the class specified
    if (person.isInstanceOf[AnotherEmployee]) {
      // if person is null, then isInstanceOf returns false
      // and asInstanceOf returns null
      val emp = person.asInstanceOf[AnotherEmployee]
      println(emp.salary)
    }

    // To test whether the object is exactly an instance of a particular class
    // use the classOf method defined in scala.Predef
    if (person.getClass == classOf[AnotherEmployee]) {
      println("person is an employee")
    }
  }

  // pattern matching is preferred for type checking
  def typeCheck(person: SimplePerson): Unit = {
    person match {
      case emp: Employee =>
        println(emp.asInstanceOf[Employee].salary)
      case _ => println("Not an employee")
    }
  }
  //===============================================================================

  //===============================================================================
  // protected keyword makes a method or field accessible to all subclasses
  // protected member can have visibility throughout the package
  // using protected[packageName].
  // protected[this] - is similar to private[this]
  class Person {
    var name: String = ""

    protected def popularity: String = "I am a simple person"

    protected[this] def feature: String = "No special feature"
  }

  class Celebrity extends Person {
    override protected def popularity: String = "I am a celebrity"

    // protected[this] members can be overridden in subclasses
    // also we can call the parent member within same instance
    override protected[this] def feature: String = "Acting" + super.feature

    def compareFeatures(person: Person) : Boolean = {
      // within this method I cannot access person.feature
      // because person is another object and we have visibility
      // only within the same object
      false
    }
  }
  //===============================================================================

  //===============================================================================
  // Only the primary constructor of subclass only
  // can call one of the superclass constructors

  class PersonWithConstructor(name: String, age: Int)

  class EmployeeWithConstructor(name: String, age: Int, salary: Double)
    extends PersonWithConstructor(name, age)

  //===============================================================================
  // Overriding fields is possible in scala since a val or var field when compiled consists
  // of private field and getter and setter methods
  // a def can override another def
  // a val can override another val or parameterless def
  // a var can override only an abstract var
  class CitizenOverriding(val name: String, val age: Int) {
    val codeName: String = "Citizen"
  }

  class SecretAgentOverriding(name: String, age: Int)
    extends CitizenOverriding(name, age) {
    override val codeName: String = "SecretAgent"
  }
  //===============================================================================

  //===============================================================================
  // Anonymous subclasses
  val anonymousPerson = new CitizenOverriding("John", 25) {
    override val codeName: String = "NonResident"
  }
  println(anonymousPerson.isInstanceOf[CitizenOverriding])

  def getCodeName(person: CitizenOverriding): String = person.codeName
  println(getCodeName(anonymousPerson))

  // This syntax is based on something called structural type
  // which is an advanced topic in scala

  def getCodeNameAdv(person: CitizenOverriding{val codeName: String}): String =
    person.codeName
  println(getCodeNameAdv(anonymousPerson))
  //===============================================================================

  //===============================================================================
  // These examples are just to demonstrate the syntax capability
  // Abstract classes
  // members are not declared with abstract keyword
  // Methods and fields can be abstract
  // Rather the class is made abstract when it contains one or more abstract members
  abstract class AbstractCitizen(val name: String, val age: Int) {
    // This is an abstract field
    // For val only abstract getter method is generated
    // For var abstract methods for getter and setter are generated
    // no field will be present in the generated abstract class
    val codeName:String

    //Abstract field can also be a var
    var alive: Boolean

    // this is abstract method
    def compare(citizen: AbstractCitizen): Boolean
  }

  class ConcreteCitizen(name: String, age: Int)
  extends AbstractCitizen(name, age) {
    // using override keyword is optional but make it more readable
    // showing that this field is an abstract field
    override val codeName: String = "Citizen"

    override var alive: Boolean = true

    override def compare(citizen: AbstractCitizen): Boolean = {
      this.codeName == citizen.codeName
    }
  }

  // Abstract classes can also be instantiated using anonymous subtypes
  val anonymousConcreteCitizen = new AbstractCitizen("Jane", 25) {
    override val codeName: String = "NonResident"
    override var alive: Boolean = true
    override def compare(citizen: AbstractCitizen): Boolean = false
  }

  //===============================================================================

  //===============================================================================
  // Book contains a very good example of issues with constructor initialization
  // order and early definitions where we can make a code in subclass execute prior
  // to the superclass constructor body
}
