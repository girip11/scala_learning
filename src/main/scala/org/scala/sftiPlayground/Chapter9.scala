package org.scala.sftiPlayground

// scalastyle:off regex.println

import java.io.File

import scala.io.{Source, StdIn}
import scala.util.matching.Regex.Match

object Chapter9 extends App {

  def getFileContents(fileName: String): String = {
    val source = Source.fromFile(fileName)
    // whole file contents as a string
    val contents = source.mkString
    source.close()
    contents
  }

  val contents = getFileContents("src/main/resources/sampleTextFile.txt")
  println(contents.split('\n').mkString(" "))

  for {
    line <- contents.split("\n")
  } println(line)

  //getLines returns an iterator
  //withClose callback gets called when the file gets closed
  val stocks = Source.fromFile("src/main/resources/stocks.csv")
    .withClose(() => println("file is being closed"))

  for {
    line <- stocks.getLines
  } println(line)
  stocks.close()

  //Reading a file character by character
  val textFile = Source.fromFile("src/main/resources/sampleTextFile.txt")
  // by default the source itself is an Iterator[Char]
  for {
    c <- textFile
  } println(c)
  textFile.close()

  //Reading from Standard input interactively
  // StdIn has other methods like readInt, readDouble
//  val fromStdIn = StdIn.readLine("Enter your favorite programming language:")
//  println(fromStdIn)

  // Alternatively we can also use Source to read from Standard input
  // This is useful when we have some contents already piped to
  // the current process standard input.
//  val fromStdIn2 = Source.stdin
//  println(fromStdIn2.mkString)
//  fromStdIn2.close()

  // Reading from a URL
  //val urlContents = Source.fromURL("http://horstmann.com", "UTF-8")

  for {
    // reads character by character
    c <- Source.fromString("Hello World")
  } println(c)

  //Scala has no built in support for reading binary files as well as writing to a file
  //we have to use java classes for this
  // Read binary files using FileInputStream
  //java.io.PrintWriter is used for writing to files


  //For directory traversing also we need to use java classes under java.nio.file package

  import java.nio.file.{Files, Paths}
  val dirStream = Files.list(Paths.get("src/main/resources"))
  try {
    for {
      fileName <- dirStream.toArray
    } println(fileName)
  } finally {
    dirStream.close()
  }

  // This makes the class Person serializable
  class Person extends Serializable

  // Read and write serialized objects using ObjectInputStream and ObjectOutputStream
  // as in java.io package

  // Process Control and shell scripting in scala
  // Refer to the file ScriptingTryouts.scala

  // Regular expressions
  import scala.util.matching.Regex

  // one or more numbers
  val numberPattern: Regex = "[0-9]+".r

  // find and iterate through all the matches using findAllIn
  // findAllIn returns Iterator[String]
  for {
    num <- numberPattern.findAllIn("I am a 25 year old and I have 10 horses.")
  } println(num)

  println(numberPattern.findFirstIn("1 2 3 4 5 "))

  // To check is a whole string matches a regular expression
  // 1. we can use regex of the form ^..$ (start to end) or
  // 2. we can use the string.matches method which checks
  // if the entire string matches the regex
  println("99999".matches(numberPattern.toString())) // true
  println("A9999".matches(numberPattern.toString())) // false

  //Finding all the matches as Match object using findFirstMatchIn and findAllMatchIn
  // match object contains
  // 1. Index/location of the match in the source string
  // 2. The matched substring as an iterable
  // 3. source string
  // 4. group names and groups
  val matches: Iterator[Match] = numberPattern.
    findAllMatchIn("India won the 1983 and 2011 Cricket world cup")

  for {
    m <- matches
  } {
    println(m.start) //start index of match in source string
    println(m.end) // end index of the match in the source string
    println(m.source) // original string
    println(m.matched) // matched string
    println(m.before) // string before the match
    println(m.after) // string after the match
  }

  // replacing the matches
  println(numberPattern.replaceFirstIn("India won the 1983 and 2011 Cricket world cup", "____"))
  println(numberPattern.replaceAllIn("India won the 1983 and 2011 Cricket world cup", "____"))

  // Replacing some matches only
  // (match: Match) => Option[String] is the signature of the replacer
  // replace even values with E
  println(numberPattern.replaceSomeIn("11 12 13 14 15 16 17",
    matchObj => if (matchObj.matched.toInt % 2 ==0) Some("EVEN") else None))

  // Regular expression groups - subexpressions of regular expressions

  //declaring regex pattern with groups and group names
  val numAndwordPattern = "([0-9]+) ([a-z]+)".r("num", "word")

  for {
   m <- numAndwordPattern.findAllMatchIn("1 hour 28 minutes 30 seconds")
  } {
    println(m.matched) // matched string as per regex
    println(m.groupNames) // all the group names
    println(m.groupCount) // total group count in the regex
    println(m.group("num")) // matched value of group named num in this match
    // group index ranges from 0,1 and 2
    // group index 0 - entire match (same as m.matched)
    // group index 1 - first group (num)
    // group index 2 - second group (word)
    println(m.group(2)) // matched value of first group which is num in this case

    // similarly we can access before and after values for each group match
    println(m.start(1), m.end(1)) // start and end index of the first group match in source
    println(m.start(2), m.end(2))
    println(m.before(1), m.after(1))
    println(m.before(2), m.after(2))
    println("====================================")
  }

  // we can also use the extractor pattern for regex matching
  val numAndwordPattern(num, word) = "100 rupees"
  println(num, word)

  try {
    // We should have an exact match for each group in the regex
    // exactly once otherwise we get MatchError
    // This raises match error because we have multiple match
    // With extractor, the whole string should match the pattern
    // exactly once
    val numAndwordPattern(num, word) = "2019 Goodbye 2020 Hello"
    println(num, word)
  } catch {
    case ex: MatchError => println(ex)
  }
}

