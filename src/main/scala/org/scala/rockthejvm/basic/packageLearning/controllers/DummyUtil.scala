package org.scala.rockthejvm.basic.packageLearning

//I can also define members in another package from this file
//though it is not a recommended practice

// This is useful in case where we might want to extend a
//third party library without touching their source
package utils {
  object DummyUtil {
    //    we can access all the members of the utils package in this scope
    def isDataValid(in: String): Boolean = StringUtil.isWhiteSpace(in)

    def getDummyData: String = "Lorem ipsum"
  }
}
