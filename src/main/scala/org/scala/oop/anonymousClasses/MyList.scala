package org.scala.oop.anonymousClasses

abstract class MyList[+A] {
  val head: A
  val tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = s"[${this.printElements.trim()}]"
  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]
  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object EmptyList extends MyList[Nothing] {

  override lazy val head: Nothing = throw new NoSuchElementException

  override lazy val tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyList[B] = new MyConcreteList(element, this)

  override def printElements: String = ""

  override def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] =
    EmptyList

  override def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] =
    EmptyList

  override def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] =
    EmptyList

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}


case class MyConcreteList[+A](override val head: A, override val tail: MyList[A])
  extends MyList[A] {

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyList[B] = new MyConcreteList(element, this)

  override def printElements: String = s"${this.head} ${tail.printElements}"

  override def filter(predicate: MyPredicate[A]): MyList[A] = {
//    def filterHelper(source: MyList[A], target: MyList[A]): MyList[A] = {
//      if (source.isEmpty) target
//      else {
//        val newTarget =  if (predicate.test(source.head)) target.add(source.head) else target
//        filterHelper(source.tail, newTarget)
//      }
//    }
//
//    filterHelper(this, EmptyList)
    if (predicate.test(this.head)) new MyConcreteList(this.head, this.tail.filter(predicate))
    else this.tail.filter(predicate)
  }

  override def map[B](transformer: MyTransformer[A, B]): MyList[B] =  {
    new MyConcreteList[B](transformer.transform(this.head), this.tail.map(transformer))
  }

  override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
    transformer.transform(this.head) ++ this.tail.flatMap(transformer)
  }

  override def ++[B >: A](list: MyList[B]): MyList[B] =
    new MyConcreteList[B](this.head, this.tail ++ list)
}
