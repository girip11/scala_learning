package org.scala.learning

class Counter(value: Int) {

  def currentCount: Int = this.value

  // Rather than modifying the state/value of count variable
  // we return new instance of counter making the implementation immutable
  def increment(n: Int = 1): Counter =
    if (n == 0) this
    else new Counter(this.value + n)

  def decrement(n: Int = 1): Counter =
    if (n == 0) this
    else new Counter(this.value - n)
}
