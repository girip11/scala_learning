package org.scala.packageLearning

//This is a package object in scala
//members of this package object are accessible to
//all the members of the controllers package.
package object controllers {
  val DEFAULT_DATA = "DummyData"
}


package controllers {
    class DummyClass extends TestTrait {
//          This is to demonstrate
//          that we can access members of parent package
//          without any import statement using this braces notation
    }
}
