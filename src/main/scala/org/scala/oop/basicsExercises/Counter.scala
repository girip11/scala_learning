package org.scala.oop.basicsExercises

class Counter(value: Int) {

  def currentCount: Int = this.value

  def inc: Counter = this.inc(1)

  def dec: Counter = this.dec(1)

  def inc(n: Int): Counter = new Counter(this.value + n)

  def dec(n: Int): Counter = new Counter(this.value - n)

  def +(n: Int): Counter = this.inc(n)

  def -(n: Int): Counter = this.dec(n)
}
