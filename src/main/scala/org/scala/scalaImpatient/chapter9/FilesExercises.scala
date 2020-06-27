
// scalastyle:off regex.println

import java.io.{File, FileWriter, PrintWriter}
import java.nio.file.{Files, Path, Paths}

import scala.io.Source

object FilesExercises extends App {

  println(
    """
      |1. Write a Scala code snippet that reverses the lines in a file (making the last
      |line the first one, and so on).
      |""".stripMargin)

  val reverseLineSource = Source.fromFile("src/main/resources/sampleTextFile.txt")
  reverseLineSource.getLines().toBuffer.reverse.foreach(println)
  reverseLineSource.close()

  println(
    """
      |  2. Write a Scala program that reads a file with tabs, replaces each tab
      |  with spaces so that tab stops are at n-column boundaries, and
      |  writes the result to the same file.
      |""".stripMargin)

  val tabSpacedFile = Source.fromFile("src/main/resources/tabSpacedFile.txt")
  val contents = tabSpacedFile.getLines().map(_.replaceAll("\t", "     ")).toSeq
  contents.foreach(println)
  tabSpacedFile.close()

//  import scala.collection.JavaConverters.asJavaIterableConverter
//  Files.write(Paths.get("src/main/resources/tabSpacedFile.txt"), contents.asJava)


  println(
    """
      | 3. Write a Scala code snippet that reads a file and prints all words with more
      | than 12 characters to the console. Extra credit if you can do this in a single line.
      |""".stripMargin)
  val wordsSource = Source.fromFile("src/main/resources/sampleTextFile.txt")
  wordsSource.
    getLines().
    mkString(" ").
    split(' ')
    .filter(_.length > 12)
    .foreach(println)
  wordsSource.close()

  println(
    """
      |  Write a Scala program that reads a text file containing only floating-point
      |  numbers. Print the sum, average, maximum, and minimum of the numbers
      |  in the file.
      |""".stripMargin)
  val numbersSource = Source.fromFile("src/main/resources/decimals.txt")
  // get lines returns an iterator which can be iterated only once.
  // Hence convert to a sequence for accessing multiple times
  val decimals = numbersSource.getLines().map(_.toDouble).toIndexedSeq
  println(decimals.getClass)
  println(decimals.sum, decimals.min, decimals.max, decimals.sum/decimals.length)
  numbersSource.close()


  println(
    """
      |  //5. Write a Scala program that writes the powers of 2 and their reciprocals to a
      |  //file, with the exponent ranging from 0 to 20. Line up the columns:
      |  //1 1
      |  //2 0.5
      |  //4 0.25
      |  //... ...
      |""".stripMargin)

  val powersFileWriter = new PrintWriter("src/main/resources/powers.txt")
  for {
    power <- 0 to 20
  } {
    val value = math.pow(2, power)
    powersFileWriter.println(s"$value${" " * (30-value.toString.length)}${1/value}")
  }
  powersFileWriter.close()
}

