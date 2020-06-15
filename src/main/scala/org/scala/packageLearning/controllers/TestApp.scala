
//Using this way we get all the names under that package
//packageLearning in this scope.
//Whatever is declared in this file goes under the package
//controllers.
package org.scala.packageLearning

//This package statement imports all the names under the package controllers
//as well as will take all the names defined in this file
//under the controllers package
package controllers

object TestApp extends App {

//  utils are accessible because we have imported packageLearning
//  which imports the names of all subpackages under it
  println(utils.JsonUtil.INDENT_JSON)

  //  can handle private members that are scoped by package
  println(utils.JsonUtil.nullHandling)

//  import within a particular scope
  import utils.StringUtil._
  println(isNullOrEmpty("        "))

  val testController = new TestController
//  members of the package object of controller is also visible
  testController.create(DEFAULT_DATA)

  testController.create(utils.DummyUtil.getDummyData)

  val dummy = new DummyClass
}