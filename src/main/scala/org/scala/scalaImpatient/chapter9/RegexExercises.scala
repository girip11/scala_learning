package org.scala.scalaImpatient.chapter9

// scalastyle:off regex.println

import java.io.FileFilter
import java.nio.file.{FileVisitOption, Files, Path, Paths}

import scala.util.matching.Regex
import scala.io.Source

object RegexExercises extends App {

  println(
    """  6. Make a regular expression searching for quoted strings "like this, maybe with
      |  \" or \\" in a Java or C++ program. Write a Scala program that prints out all
      |  such strings in a source file.
      |
      |""".stripMargin)


  println(
    """  7. Write a Scala program that reads a text file and prints all tokens in the file
      |  that are not floating-point numbers. Use a regular expression.
      |
      |""".stripMargin)

  def printNonFloatingPointTokens(fileContent: String, pattern: String): Unit = {
    val tokens = fileContent.split("\\s+")
    // But this will also filter out valid non floating point numbers
    tokens.foreach(token => {
      if (!(token matches pattern))
        println(token)
    })
  }

  val dummyFileContent = "I have 2.5 and 5.5 litre cookers bought in 2010"
  printNonFloatingPointTokens(dummyFileContent, "\\d+\\Q.\\E\\d+")

  println(
    """
      |  8. Write a Scala program that prints the src attributes of all img tags of a web
      |  page. Use regular expressions and groups.
      |
      |""".stripMargin)

  val text =
    """hello <img src="https://mdn.mozillademos.org/files/12708/image-with-title.png"
     alt="The dinosaur image"
     style="display: block; height: 341px; margin: 0px auto; width: 400px;">"""

  val htmlSource = Source.fromString(text)
  val htmlContents = htmlSource.mkString
  val imgRegex = "(?s)<img.*src=(?:[\"'])([^\"']+).*>".r
  println(imgRegex.findFirstMatchIn(htmlContents).get.group(1))

  htmlSource.close()

  println(
    """  9. Write a Scala program that counts how many files with .class extension are
      |  in a given directory and its subdirectories.
      |
      |""".stripMargin)
  import scala.collection.JavaConverters.asScalaIteratorConverter
  def countClassFiles(dirPath: Path) : Int = {
    // if the directory is empty
    // return 0
    // return count all .class files in this directory +
    // count all the .class files in this directory's subdirectories

    val filesAndSubdirectories = Files.list(dirPath).iterator().asScala.toList
    if (filesAndSubdirectories.isEmpty)
      0
    else
      getClassFiles(filesAndSubdirectories) +
        getSubdirectories(filesAndSubdirectories).map(countClassFiles(_)).sum
  }

  def getClassFiles(filesAndSubdirectories: Iterable[Path]): Int = {
    filesAndSubdirectories.count(path => {
      val file = path.toFile()
      file.isFile && file.getName.matches(".*\\.class")
    })
  }

  def getSubdirectories(filesAndSubdirectories: Iterable[Path]): Iterable[Path] = {
    filesAndSubdirectories.filter(_.toFile.isDirectory)
  }

  println(countClassFiles(Paths.get("./target/scala-2.12/classes/org/scala")))

  import java.io.File

  // Solution 2- more concise one
  // Reference:
  // https://github.com/derlin/scala-for-the-impatient/blob/master/src/main/scala/chapter09.sc
  def walkFolderTree(rootDir: File) : Iterable[File] = {
    // if the directory is empty
    // return 0
    // return count all .class files in this directory +
    // count all the .class files in this directory's subdirectories

    val children = rootDir.listFiles
    children.filter(_.isFile).toIterable ++
    children.filter(_.isDirectory).flatMap(walkFolderTree(_))
  }

  val files = walkFolderTree(new File("./target/scala-2.12/classes/org/scala"))
  println(files.count(file => file.getName.matches(".*\\.class")))


  println(
    """  10. Expand the example in Section 9.8, “Serialization,” on page 113. Construct a
      |  few Person objects, make some of them friends of others, and save an
      |  Array[Person] to a file. Read the array back in and verify that the friend relations
      |  are intact.
      |
      |""".stripMargin)

}
