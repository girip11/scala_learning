package org.scala.packageLearning.utils

object JsonUtil {
  val INDENT_JSON:Boolean = true

//  Any entity under the package packageLearning can access this
//  member
  private[packageLearning] var nullHandling: Boolean = false

//  Just placeholder implementations
  def serialize[T](obj: T) : String = obj.toString()

  def deserialize[T](json: String, objType: Class[T]) : T = json.asInstanceOf[T]
}
