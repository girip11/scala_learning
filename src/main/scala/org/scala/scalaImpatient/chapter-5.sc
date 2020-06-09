

//1. Improve the Counter class in Section 5.1, “Simple Classes and Parameterless Methods,”
// on page 55 so that it doesn't turn negative at Int.MaxValue

class Counter {
  private var value = 0

  def increment() {
    if (this.value == Int.MaxValue)
    // reset the counter to 0
    // Or you can also stop incrementing when the value reaches Int.MaxValue
      this.value = 0
    else
      this.value += 1
  }

  def current: Int = value
}

// 2. Write a class BankAccount with methods deposit and withdraw, and a read-only
//property balance.

class BankAccount(initialBalance: Double = 0.0){
  private var _balance: Double = initialBalance

  def balance: Double = this._balance

  def deposit(value: Double): Double = {
    this._balance += value
    this._balance
  }

  def withdraw(value: Double): Double = {
    if (_balance < value)
      throw new IllegalStateException("Insufficient balance")

    this._balance -= value
    value
  }
}

//3. Write a class Time with read-only properties hours and minutes and a method
//before(other: Time): Boolean that checks whether this time comes before the
//other. A Time object should be constructed as new Time(hrs, min), where hrs is in
//military time format (between 0 and 23).

class Time(val hours: Int, val minutes: Int) {
  require(hours >=0 && hours < 24, "Hours should be between 0 and 23")
  require(minutes >=0 && minutes < 59, "Minutes should be between 0 and 59")

  def before(other: Time): Boolean = {
    this.hours < other.hours ||
      (this.hours == other.hours && this.minutes < other.minutes)
  }
}

println(new Time(5,20).before(new Time(6, 20)) == true)
println(new Time(5,20).before(new Time(5, 20)) == false)
println(new Time(5,20).before(new Time(4, 20)) == false)
println(new Time(5,10).before(new Time(5, 20)) == true)

//4. Reimplement the Time class from the preceding exercise so that the internal
//representation is the number of minutes since midnight (between 0 and 24 ×
//60 – 1). Do not change the public interface. That is, client code should be
//unaffected by your change.

class TimeNew(val hours: Int, val minutes: Int) {
  require(hours >=0 && hours < 24, "Hours should be between 0 and 23")
  require(minutes >=0 && minutes < 59, "Minutes should be between 0 and 59")

  private val totalMinutes: Int = (hours * 60) + minutes

  def before(other: TimeNew): Boolean = {
    this.totalMinutes < other.totalMinutes
  }
}

println(new TimeNew(5,20).before(new TimeNew(6, 20)) == true)
println(new TimeNew(5,20).before(new TimeNew(5, 20)) == false)
println(new TimeNew(5,20).before(new TimeNew(4, 20)) == false)
println(new TimeNew(5,10).before(new TimeNew(5, 20)) == true)

//5. Make a class Student with read-write JavaBeans properties name (of type String)
//and id (of type Long). What methods are generated? (Use javap to check.) Can
//you call the JavaBeans getters and setters in Scala? Should you?

import scala.beans.BeanProperty

class Student(@BeanProperty var name: String, @BeanProperty var id: Long)

//:javap -private Student
// Yes, We can call the JavaBeans getters and setters in scala, but idiomatically
//we should avoid using java beans getters and setters

//6. In the Person class of Section 5.1, “Simple Classes and Parameterless Methods,”
//on page 55, provide a primary constructor that turns negative ages to 0.

class Person6(val name: String, _age: Int = 0) {
//  remember primary constructor always executes the body of the class
  val age = if (_age >= 0) _age else 0

}

//7. Write a class Person with a primary constructor that accepts a string containing
//a first name, a space, and a last name, such as new Person("Fred Smith"). Supply
//read-only properties firstName and lastName. Should the primary constructor
//parameter be a var, a val, or a plain parameter? Why?

//primary constructor can be a val if the full name is required by the client
//If the client only needs the first name and last name properties
//then we can make the name as class parameter. But we don't need to make the
//name as var
class Person7(name: String) {
  private val _firstName: String = name.split(' ').head
  private val _lastName: String = name.split(' ').last
  def firstName: String = this._firstName
  def lastName: String = this._lastName
}


//8. Make a class Car with read-only properties for manufacturer, model name,
//and model year, and a read-write property for the license plate. Supply four
//constructors. All require the manufacturer and model name. Optionally,
//model year and license plate can also be specified in the constructor. If not,
//the model year is set to -1 and the license plate to the empty string. Which
//constructor are you choosing as the primary constructor? Why?

class Car(val manufacturer: String,
          val modelName: String,
          val modelYear: Int,
          var licensePlate: String){

  def this(manufacturer: String, modelName: String) {
    this(manufacturer, modelName, -1, "")
  }

  def this(manufacturer: String, modelName: String, modelYear: Int) {
    this(manufacturer, modelName, modelYear, "")
  }

  def this(manufacturer: String, modelName: String, licensePlate: String) {
    this(manufacturer, modelName, -1, licensePlate)
  }
}

//9. Reimplement the class of the preceding exercise in Java, C#, or C++ (your
//choice). How much shorter is the Scala class?

//10. Consider the class
//class Employee(val name: String, var salary: Double) {
//def this() { this("John Q. Public", 0.0) }
//}
//Rewrite it to use explicit fields and a default primary constructor. Which form
//do you prefer? Why

class Employee(val name: String = "John Q. Public", var salary: Double = 0.0)

// primary constructor with default parameters is concise and readable
// So this approach is preferred