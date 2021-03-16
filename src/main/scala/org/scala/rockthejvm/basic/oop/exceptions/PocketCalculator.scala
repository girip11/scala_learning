package org.scala.rockthejvm.basic.oop.exceptions

trait Calculator {
  def add(x: Int, y: Int): Int
  def subtract(x: Int, y: Int): Int
  def multiply(x: Int, y: Int): Int
  def divide(x: Int, y: Int): Int
}

class OverflowException extends RuntimeException
class UnderflowException extends RuntimeException
class MathCalculationException extends RuntimeException("Division by 0")

class PocketCalculator extends Calculator {
  override def add(x: Int, y: Int): Int = {
    val sum = x + y
    if (x > 0 && y > 0 && sum <= 0) throw new OverflowException
    else if (x < 0 && y < 0 && sum >= 0) throw new UnderflowException
    else sum
  }

  override def subtract(x: Int, y: Int): Int = {
    val diff = x - y

    if (x > 0 && y < 0 && diff < 0) throw new OverflowException
    else if (x < 0 && y > 0 && diff > 0) throw new UnderflowException
    else diff
  }

  override def multiply(x: Int, y: Int): Int = {
    // This method is still buggy
    // does not throw underflow exception for Int.MinValue * 3

    val product = x * y

    if ((x > 0 && y > 0 && product <= 0) || (x < 0 && y < 0 && product <= 0))
      throw new OverflowException
    else if ((x > 0 && y < 0 && product >= 0) || (x < 0 && y > 0 && product >= 0))
      throw new UnderflowException

    product
  }

  override def divide(x: Int, y: Int): Int = {
    if (y == 0) throw new MathCalculationException
    x / y
  }
}
