//This will import all names under the package controllers only
//In chained syntax, all the names of the last member in the chain
//are made available to the current file
package org.scala.packageLearning.controllers

//To import members from subpackages of packageLearning
// we have to do it explicitly due to the above chained package syntax
import org.scala.packageLearning.utils.JsonUtil

//Dummy controller for demonstration purposes
class TestController {
  private var data: String = ""

  def create(testData: String): Unit = {
    this.data = testData
    println(s"Data created: $testData")
  }

  def get: String = {
    JsonUtil.deserialize(this.data, classOf[String])
  }

  def delete(testData: String): Unit = {
    this.data = ""
    println(s"Data deleted: $testData")
  }
}
