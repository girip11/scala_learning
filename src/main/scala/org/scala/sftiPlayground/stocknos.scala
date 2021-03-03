package org.scala.sftiPlayground

class StockNos(val units: Int) extends AnyVal {
  // providing method for multiplication
  def *(other: Int): StockNos = new StockNos(this.units * other)

  override def toString = s"StockNos(${this.units})"
}

object StockNosMain extends App {
  val stockNos = new StockNos(5)
  println(stockNos * 2)
}
