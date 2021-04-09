package org.scala.rockthejvm.advanced.advfp

// scalastyle:off regex.println
// Rule IDs to turn off can be found here
// http://www.scalastyle.org/rules-dev.html#org_scalastyle_scalariform_MethodNamesChecker
// scalastyle:off method.name
/**
 *
 */
trait MySet[A] extends (A => Boolean) {
  override def apply(elem: A): Boolean = contains(elem)

  def isEmpty: Boolean
  def head: A
  def tail: MySet[A]

  def contains(elem: A): Boolean
  def +(elem: A): MySet[A]
  def ++(anotherSet: MySet[A]): MySet[A]

  def map[B](f: A => B): MySet[B]
  def flatMap[B](f: A => MySet[B]): MySet[B]
  def filter(predicate: A => Boolean): MySet[A]
  def foreach(f: A => Unit): Unit
}

class EmptySet[A] extends MySet[A] {
  override def contains(elem: A): Boolean = false

  override def isEmpty: Boolean = true

  override def head: A = throw new NoSuchElementException

  override def tail: MySet[A] = throw new NoSuchElementException

  override def +(elem: A): MySet[A] = new NonEmptySet(elem, this)

  override def ++(anotherSet: MySet[A]): MySet[A] =
    if (anotherSet.isEmpty) this
    else anotherSet

  override def map[B](f: A => B): MySet[B] = new EmptySet[B]

  override def flatMap[B](f: A => MySet[B]): MySet[B] = new EmptySet[B]

  override def filter(predicate: A => Boolean): MySet[A] = this

  override def foreach(f: A => Unit): Unit = ()

}

class NonEmptySet[A](override val head: A, override val tail: MySet[A]) extends MySet[A] {
  override def isEmpty: Boolean = false

  override def contains(elem: A): Boolean =
    (this.head == elem) || this.tail.contains(elem)

  override def +(elem: A): MySet[A] =
    if (this.contains(elem)) this
    else new NonEmptySet(elem, this)

  override def ++(anotherSet: MySet[A]): MySet[A] =
    if (anotherSet.isEmpty) this
    else (this + anotherSet.head) ++ anotherSet.tail

  override def map[B](f: A => B): MySet[B] =
    new NonEmptySet(f(head), this.tail.map(f))

  override def flatMap[B](f: A => MySet[B]): MySet[B] =
    f(this.head) ++ this.tail.flatMap(f)

  override def filter(predicate: A => Boolean): MySet[A] = {
    if (predicate(this.head))
      new NonEmptySet(this.head, this.tail.filter(predicate))
    else
      this.tail.filter(predicate)
  }

  override def foreach(f: A => Unit): Unit = {
    f(this.head)
    if (!this.tail.isEmpty) {
      this.tail.foreach(f)
    }
  }
}

object FunctionalCollection extends App {
  val someSet1 = new NonEmptySet(1, new NonEmptySet(2, new NonEmptySet(3, new EmptySet)))
  val someSet2 = new NonEmptySet(4, new NonEmptySet(5, new NonEmptySet(1, new EmptySet)))

  val extendedSet = someSet1 ++ someSet2
  (extendedSet + 6).foreach(println)

  extendedSet.map(_ + 1).foreach(println)

  extendedSet.flatMap(new NonEmptySet(_, new EmptySet)).foreach(println)
}
