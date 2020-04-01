package org.scala.oop

// TODO implement using the same approach as the instructor
abstract class MyList[A] {
  def head: A
  def tail: A
  def isEmpty: Boolean
  def add(value: A): Unit
  def filter(predicate: MyPredicate[A]): MyList[A]
  def forEach[B](transformer: MyTransformer[A, B]): Unit
  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
}

class MyConcreteList[A] extends MyList[A] {

  private class Node(val value: A, next: Node) {
    private var _next = next
    def setNext(next: Node): Unit = this._next = next
    def getNext: Node = this._next
  }

  private var _head: Node = null
  private var _tail: Node = null

  def head: A =
    if (this._head == null) throw new NoSuchElementException
    else this._head.value

  def tail: A =
    if (this._tail == null) throw new NoSuchElementException
    else this._tail.value

  def isEmpty: Boolean = this._head == null

  def add(value: A): Unit = {
    val node = new Node(value, null)
    if (this._head == null) {
      this._head = node
    } else if (this._head.getNext == null) {
      this._head.setNext(node)
    }

    if (this._tail == null) {
      this._tail = node
    } else {
      this._tail.setNext(node)
      this._tail = node
    }
  }

  override def toString(): String = {
    def iterate(item: Node, acc: String): String =
      if (item == null) acc
      else iterate(item.getNext, s"$acc ${item.value},")

    s"[ ${iterate(this._head, "")} ]"
  }

  def filter(predicate: MyPredicate[A]): MyList[A] = {
    def executePredicate(currentList: Node, newList: MyList[A]): MyList[A] = {
      if (currentList == null) {
        return newList
      }
      if (predicate.test(currentList.value)) {
        newList.add(currentList.value)
      }

      executePredicate(currentList.getNext, newList)
    }

    executePredicate(this._head, new MyConcreteList[A])
  }

  def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
    def executeTransform(currentNode: Node, newList: MyList[B]): MyList[B] = {
      if (currentNode == null) {
        return newList
      }

      newList.add(transformer.transform(currentNode.value))
      executeTransform(currentNode.getNext, newList)
    }

    executeTransform(this._head, new MyConcreteList[B])
  }

  def forEach[B](transformer: MyTransformer[A, B]): Unit = {
    def executeTransform(currentNode: Node): Unit = {
      if (currentNode != null) {
        transformer.transform(currentNode.value)
        executeTransform(currentNode.getNext)
      }
    }

    executeTransform(this._head)
  }

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
    def executeTransform(currentNode: Node, newList: MyList[B]): MyList[B] = {
      if (currentNode == null) {
        return newList
      }
      val returnValue = transformer.transform(currentNode.value)
      returnValue.forEach(new MyTransformer[B, Unit] {
        override def transform(value: B): Unit = newList.add(value)
      })

      executeTransform(currentNode.getNext, newList)
    }

    executeTransform(this._head, new MyConcreteList[B])
  }
}
