package org.scala.fp.tuplesAndMaps

import scala.collection.mutable.Set

case class Person (val name: String) {
  private val friends = Set.empty[String]

  def addFriend(friend: String): Unit = {
    this.friends.add(friend)
  }

  def removeFriend(friend: String): Unit = {
    this.friends.remove(friend)
  }

  def isFriend(personName: String): Boolean = this.friends.contains(personName)

  def friendList: Iterable[String] = this.friends
}

