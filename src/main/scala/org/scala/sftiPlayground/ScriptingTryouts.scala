
import java.io.File
import scala.sys.process._

// scalastyle:off regex.println

// Additional references
// https://alvinalexander.com/scala/scala-execute-exec-external-system-commands-in-scala/
// https://alvinalexander.com/scala/how-to-write-scala-shell-scripts-scripting-language-examples/
// https://www.codementor.io/@martinbrosenberg/shello-world-writing-a-scala-script-z8qg4i7d4

object ScriptingTryouts extends App {

  // I can execute the same process command any times using this same handle
  val p = Process("cat sampleTextFile.txt",
    new File("src/main/resources"),
    ("ENV_TEST", "Hello World"))

  println(p.!)

  // operators &&, | , ||, >, >> and <  all need to be prefixed with #
  ("pwd" #&& p).!

  println()
  val cwd = ("pwd".!!).trim
  // writes Hello world to the file output.txt in the current folder
  // redirecting stdout to a file
  ("echo Hello world" #> new File(cwd + "/src/main/resources/output.txt")).!

  // This prints the single quotes also
  "echo 'Hello world'".!

  //reading from file as stdin
  ("grep -i scala" #< new File("src/main/resources/sampleTextFile.txt")).!

}



