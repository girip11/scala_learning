//This package statement makes all the names available under
//the package utils accessible in this entire file's scope
package org.scala.packageLearning.utils

object StringUtil {

  def isEmpty(in: String) : Boolean = in.isEmpty

  def isWhiteSpace(in: String):Boolean = in.trim.isEmpty

  def isNull(in: String): Boolean = in == null

  def isNullOrEmpty(in: String): Boolean = isNull(in) || isWhiteSpace(in)
}
