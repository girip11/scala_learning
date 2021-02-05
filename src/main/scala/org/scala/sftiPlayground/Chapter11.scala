package org.scala.sftiPlayground

// scalastyle:off regex.println

object Chapter11 extends App {

  // we can have  ! # % & * + - / : < = > ? @ \ ^ | ~  in identifiers
  val ** : (Double, Double) => Double = scala.math.pow(_, _)
  println(**(5, 2))

  // when variable names are same as identifiers use `` to escape those names
  val `val` = 100
  println(`val`)

  // Extractors
  // Extractor is an object with unapply method
  // unapply - takes an objects and extracts information from it
  // Usecase - reverse of apply method on a companion object

  class Fraction(val n: Int, val d: Int) {
    override val toString: String = s"$n/$d"
  }

  object Fraction {
    def apply(num: Int, den: Int): Fraction = {
      println("apply method on Fraction called")
      //require(den > 0, "Denominator cannot be 0")
      new Fraction(num, den)
    }

    def unapply(frac: Fraction): Option[(Int, Int)] = {
      println("unapply method on Fraction called")
      if (frac.d == 0) None else Some(frac.n, frac.d)
    }
  }

  val fraction = Fraction(3, 5) //apply method called
  val Fraction(num, den) = fraction
  println(num, den)

  // Raises MatchError when unapply returns None
  // val Fraction(num1, den1) = Fraction(10,0)

  // case classes come with default apply and unapply methods.
  // unapply on any object can be written

  // Extractor pattern on Existing objects
  // Extractor's unapply method should always return Option[something]

  import scala.util.matching.Regex
  object StockPrice {
    private val pattern: Regex = """([A-Z]+).*\$([0-9]+[.][0-9]+).*""".r

    // Extractor returning a tuple
    def unapply(input: String): Option[(String, Double)] = {
      val patternMatch = pattern.findFirstMatchIn(input)
      patternMatch.filter(_.groupCount == 2)
        .map(m => (m.group(1), m.group(2).toDouble))
    }
  }

  // Extractor pattern on the String.
  // LHS expression decides which extractor to call
  val inputText = "MSFT stock price as of 22-06-2020 is $200.57"
  val StockPrice(stock, price) = inputText
  println(stock, price)

  // I can nest extractor
  object DollarToRupeeConverter {
    // example of extractor returning a single value
    def unapply(usd: Double): Option[Double] = Some(usd * 75)
  }

  // while extracting price from inputText expression becomes
  // val DollarToRupeeConverter(inr) = price
  val StockPrice(msftStock, DollarToRupeeConverter(inr)) = inputText
  println(msftStock, inr)

  // If we need to extract a sequence of values we should implement
  // the unapplySeq method in the extractor. This method should return
  // Option[Seq[T]]
  object SportNamesExtractor {
    def unapplySeq(in: String): Option[Seq[String]] = {
      Some(in.split("@"))
    }
  }

  val email = "johndoe@example.com"
  // When we return a sequence we still need to unpack/extract the list
  // to individual variables
  val SportNamesExtractor(prefix, mailDomain) = email
  println(prefix, mailDomain)
}
