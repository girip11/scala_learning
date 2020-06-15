//using org instead of com

//References:
// 1. https://github.com/randywse/scala-for-the-impatient-1/blob/master/src/main/scala/com/basile/scala/ch07/Ex02.scala
// 2. https://gist.github.com/xiaohel/a5df5c75126f2968cd73fe643b66d623
package org.scala.scalaImpatient

object Ex02 {
  val a = 1
}

package org.scala.scalaImpatient {

  /**
   * Write a puzzler that baffles your Scala friends, using a package com that isn’t at the top level.
   */
  object Ex02 extends App {
    val a = 1

    assert( _root_.org.scala.scalaImpatient.Ex02.a == org.scala.scalaImpatient.Ex02.a)
  }

}

