// Solutions can also be checked in the repository
// https://github.com/marcus-nl/scala-impatient-2nd-ed
// https://github.com/saljuama/scala-for-the-impatient

// 6.
scala.math.BigInt(2).pow(1024)


// 7
import scala.math.BigInt._
import scala.util.Random

probablePrime(100, Random)

// 8.
val rand = probablePrime(100, Random)
rand.toString(36)

// 9.
"hello".head
"hello"(0)

"hello".last

//10
// takes 3 characters from starting
"hello".take(3)
//takes 3 characters from the end
"hello".takeRight(3)
// similarly drop drops n characters from left
// and dropRight takes n characters from right
// and returns the remaining string