package org.scala.sftiPlayground

// scalastyle:off regex.println

object Chapter10 extends App {

  // Traits as java interfaces
  trait Executor {
    def run(args: Any*): Unit
  }

  trait CommandExecutor {
    def execute(command: String, args: String*): Int
  }

  class TraitImplementation extends Executor with  CommandExecutor {
    // override keyword is optional on abstract methods of a trait
    override def run(args: Any*): Unit = ???
    override def execute(command: String, args: String*): Int  = ???
  }

  // Similar to default methods in Java interfaces
  import sys.process._

  // Traits with only concrete implementations can be mixed in with classes
  trait ShellCommandExecutor {
    def execute(command: String, args: String*): Int = {
      Process(command, args).!
    }
  }

  abstract class CommandExecutorImpl extends CommandExecutor {
    def process(command: String): Int = execute(command)
  }

  // While creating objects we can extend traits dynamically
  val obj = new CommandExecutorImpl with ShellCommandExecutor
  println(obj.process("pwd"))

  // traits can be layered
   trait A {
    println("Construction order: A's body executed")
    def simpleMethod(msg: String): Unit = {
      println(s"From A: $msg")
    }
   }

   trait B extends A {
     println("Construction order: B's body executed")
     override def simpleMethod(msg: String): Unit = {
       super.simpleMethod("Msg from B : " + msg)
     }
   }

   trait C extends A {
     println("Construction order: C's body executed")
     override def simpleMethod(msg: String): Unit = {
       super.simpleMethod("Msg from C : " + msg)
     }
   }

   class S extends A {
     def process(command: String): Unit = {
       simpleMethod("Processing command")
     }
   }

  val sObj = new S with B with C
  // Here simpleMethod of C gets called first and then
  // the call is passed to B and then to A
  // trait method invocation order is from right to left
  // while the trait construction order is from left to right
  sObj.process("ls -al")
  // Construction Order
  // B's body gets executed (as part of this A's body is
  // executed since A is parent of B) followed by C
  // Notice that the A's body is executed exactly once


  // For overriding abstract methods with traits
  // and calling parent's abstract method from child trait
  // we need to add the abstract and override keywords to the method
  // in the subtrait
  // Reason for abstract is when used directly as subtrait concrete implementation
  // needs to be provided
  // But if mixed in with some other trait that comes in the way(traits invocation order)
  // then override keyword is used
  // Refer to the book for examples


  // concrete fields from traits are not inherited but added to
  // subclass during compilation process
  // So when a trait gets modified we need to recompile all the classes
  // that extends this trait.

  // Traits can also have abstract fields
}
