package org.scala.scalaImpatient.chapter8

//4. Define an abstract class Item with methods price and description. A SimpleItem is
//an item whose price and description are specified in the constructor. Take
//advantage of the fact that a val can override a def. A Bundle is an item that
//contains other items. Its price is the sum of the prices in the bundle. Also
//provide a mechanism for adding items to the bundle and a suitable description
//method.
object Exercise4 extends App {

  import scala.collection.mutable.ArrayBuffer

  abstract class Item {
    def price: Double
    def description: String
  }

  class SimpleItem(override val price: Double,
                   override val description: String) extends Item

  class Bundle(override val description: String, otherItems: Item*) extends Item {
    private val items: ArrayBuffer[Item] = ArrayBuffer.empty[Item] ++ otherItems

    def addItem(item: Item): Unit = (items += item)

    override def price: Double = this.items.foldLeft(0.0)((acc, item: Item) => acc + item.price)
  }

  val b = new Bundle("Bundle", new SimpleItem(3.5, "Coffee"), new SimpleItem(2, "Cookie"))
  println(b)
}
