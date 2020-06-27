

// 1. Write an object Conversions with methods inchesToCentimeters,
// gallonsToLiters, and milesToKilometers.

object Conversions {
  def inchesToCentimeters(inches: Double): Double = {
    inches * 2.5
  }

  def gallonsToLiters(gallons: Double): Double = {
    gallons * 3.79
  }

  def milesToKilometers(miles: Double): Double = {
    miles * 1.6
  }
}


//2. The preceding problem wasn’t very object-oriented.
// Provide a general superclass UnitConversion and define objects
// InchesToCentimeters, GallonsToLiters, and
//MilesToKilometers that extend it.

abstract class UnitConversion {
  def convert(from: Double): Double
}

object InchesToCentimeters extends UnitConversion {
  private val CONVERSION_FACTOR: Double = 2.5
  override def convert(from: Double): Double = from * CONVERSION_FACTOR
}

object GallonsToLiters extends UnitConversion {
  private val CONVERSION_FACTOR: Double = 3.785
  override def convert(from: Double): Double = from * CONVERSION_FACTOR
}

object MilesToKilometers extends UnitConversion {
  private val CONVERSION_FACTOR: Double = 1.61
  override def convert(from: Double): Double = from * CONVERSION_FACTOR
}


//3. Define an Origin object that extends java.awt.Point. Why is this not actually a
//good idea? (Have a close look at the methods of the Point class.)

import java.awt.{Point => JPoint}

object Origin extends JPoint

//We have methods like setLocation which makes the point object mutable


//4. Define a Point class with a companion object so that you can construct Point
//instances as Point(3, 4), without using new.

object Point {
  def apply(x: Int = 0, y: Int = 0): Point = new Point(x,y)
}

//I can also make the Point constructor private if required
class Point(val x: Int = 0, val y: Int = 0) {
  override def toString: String = s"($x, $y)"
}

println(Point())
println(Point(2,4))

//5. Write a Scala application, using the App trait, that prints its command-line
//arguments in reverse order, separated by spaces. For example, scala Reverse
//Hello World should print World Hello.

//object Chapter6App extends App {
//  println(args.reverse.mkString(" "))
//}

//6. Write an enumeration describing the four playing card suits so that the toString
//method returns ß, ®, ©, or ™.

object PlayingCard extends Enumeration {
  type PlayingCard = Value
  //  Assigning a name to these returns
  //  the toString representation when used
  val Spade = Value("\u2664")
  val Heart = Value("\u2661")
  val Diamond = new Val {
    override def toString(): String = "\u2662"
  }
  val Club = Value("\u2663")
}

println(PlayingCard.Club)
println(PlayingCard.Diamond)

for {
card <- PlayingCard.values
} println(card)

//7. Implement a function that checks whether a card suit value from the preceding
//exercise is red.


def isPlayingCardRed(playingCard: PlayingCard.PlayingCard) : Boolean =
  playingCard == PlayingCard.Diamond || playingCard == PlayingCard.Heart

println(isPlayingCardRed(PlayingCard.Diamond))
println(isPlayingCardRed(PlayingCard.Spade))

//8. Write an enumeration describing the eight corners of the RGB color cube. As
//IDs, use the color values (for example, 0xff0000 for Red).
object RGBColorCube extends Enumeration {
  type RGBColor = Value
  val Black = Value(0x000000)   // (0, 0, 0)
  val Blue = Value(0x0000ff)    // (0, 0, 1)
  val Green  = Value(0x00ff00)  // (0, 1, 0)
  val Cyan  = Value(0x00ffff)   // (0, 1, 1)
  val Red  = Value(0xff0000)    // (1, 0, 0)
  val Magenta  = Value(0xff00ff)// (1, 0, 1)
  val Yellow  = Value(0xffff00) // (1, 1, 0)
  val White = Value(0xffffff)   // (1, 1, 1)
}