import java.time.LocalDate

import scala.util.Try

// 1.
def getSignum(n: Int): Int = {
  if (n < 0) -1
  else if (n > 0) 1
  else 0
}

getSignum(100)
getSignum(-10)

// using pattern matching
def getSignumPatMatch(n: Int): Int = {
  n match {
    case positive: Int if positive > 0 => 1
    case negative: Int if negative < 0 => -1
    case _ => 0
  }
}

getSignumPatMatch(100)
getSignumPatMatch(-10)
getSignumPatMatch(0)

//2. Return value is () and Unit is the return type
// of empty block expression
val emptyBlock: Unit = {}
println(emptyBlock)

// 3. scenario where x=y=1 is valid
var x: Unit = ()
var y: Int = 0
x = y = 1

// 4. Scala for loop
for (i <- (0 to 10).reverse) {
  println(i)
}

// 5. Countdown procedure counting from n to 0

def countdown(n: Int): Unit = {
  if (n >= 0) {
    println(n)
    countdown(n-1)
  }
}

countdown(5)

// 6. product of unicode codes of all letters in a string
var product: Long = 1
val inputString = "Hello"
for {
  ch <- inputString
} product *= ch.toLong

println(product)

// 7. above without loop and using a method from StringOps
"Hello".foldLeft(1L)((prod: Long, ch: Char) => prod * ch.toLong)

// 8 and 9 Function to compute product of characters in a String
def computeProduct(input: String): Long = {
  if (input == "") 1
  else input(0).toLong * computeProduct(input.substring(1))
}
computeProduct("Hello")

// Tail recursive
def computeProductTR(input: String): Long = {
  def computeProductHelper(input: String, acc: Long = 1): Long = {
    if (input == "") acc
    else computeProductHelper(input.substring(1), input(0).toLong * acc)
  }
  computeProductHelper(input)
}
computeProductTR("Hello")

// 10. compute x power n
def computePower(x: Int, n: Int): Double = {
  if (n == 0) 1
  else if (n < 0) (1 / computePower(x, -n))
  else if (n % 2  != 0) x * computePower(x, n - 1)
  else computePower(x, n/2) * computePower(x, n/2)
}

computePower(3, 3)

// 11.

implicit class DateInterpolator(val sc: StringContext) extends AnyVal {
  def date(args: Any*): LocalDate = {
    if (args.length != 3)
      throw new IllegalArgumentException("Arguments should contain year, month and day values")

    val date: Try[LocalDate] = for {
      year <- Try {args(0).toString().toInt}
      month <- Try {args(1).toString().toInt}
      day <- Try {args(2).toString().toInt}
    } yield LocalDate.of(year, month, day)

    if (date.isFailure)
      throw date.failed.get
    else
      date.get
  }
}

val year = 2020
val month = 6
val day = 3
val strDay = "abc"
date"$year-$month-$day"
Try{date"$year-$month"}
Try{date"$year-$month-$strDay"}