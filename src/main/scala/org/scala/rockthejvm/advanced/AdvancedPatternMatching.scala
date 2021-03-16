package org.scala.rockthejvm.advanced

//scalastyle:off regex.println

/**
 *
 */
object AdvancedPatternMatching extends App {
  // This lecture explains writing and using custom extractors in case statements.

  // This is the exercise problem from that lecture
  val n: Int = 45
  // refactor the following match statement to be even more readable
  val mathProperty = n match {
    case x if x < 10 => "single digit"
    case x if x % 2 == 0 => "even number"
    case _ => "no property"
  }
  println(s"Original: $n has property $mathProperty")

  // Solution1: I am going to define an extractor that extracts
  // the property of a number
  object MathProperty {
    def unapply(number: Int): Option[String] = {
      Some(number match {
        case x if x < 10 => "single digit"
        case x if x % 2 == 0 => "even number"
        case _ => "no property"
      })
    }
  }

  val MathProperty(property) = n
  println(s"Sol1: $n has property $property")

  // ======================================================================
  // Solution2: Define different extractors for single digit, even number
  object SingleDigitNumber {
    // Or I could just return Boolean in the unapply method
    // But I opted for the string return value
    // def unapply(n: Int): Boolean = (n >= 0 && n < 10)

    def unapply(n: Int): Option[String] =
      if (n >= 0 && n < 10) Some("single digit") else None
  }

  object EvenNumber {
    // Or I could just return Boolean in the unapply method
    // def unapply(n: Int): Boolean = (n % 2 == 0)

    def unapply(n: Int): Option[String] = if (n % 2 == 0) Some("an even number") else None
  }

  def getNumberProperty(number: Int): String = {
    number match {
      case SingleDigitNumber(single_digit) => single_digit
      case EvenNumber(even) => even
      case _ => "no property"
    }
  }

  println(s"Sol2. $n has property ${getNumberProperty(n)}")
}
