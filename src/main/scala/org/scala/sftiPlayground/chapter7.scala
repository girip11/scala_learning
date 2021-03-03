package org.scala.sftiPlayground

package com {
  object ComConstants {
    val insideCom = "Foo"
  }
  package app {

    object AppConstants {
      val insideApp = "Bar"
    }

    package hello {
      object Utils {
        //I can access names from packages com and scala
        println(ComConstants.insideCom)
        println(AppConstants.insideApp)
        def sayHello(name: String): Unit = println(s"Hello, $name")
      }
    }

    package world {
      object Main extends App {
        hello.Utils.sayHello("world")
      }
    }
  }
}
