package org.scala.fp.tuplesAndMaps

import scala.collection.mutable.Map
import scala.util.Try

class Network {
  private val socialNetwork: Map[String, Person] = Map.empty[String, Person]

  def addToNetwork(person: String): Unit = {
    if (!this.socialNetwork.contains(person))
        this.socialNetwork.put(person, Person(person))
  }

  def removeFromNetwork(person: String): Unit = {
    val personToRemove: Option[Person] = this.socialNetwork.get(person)

    personToRemove.foreach(p => {
      p.friendList.foreach(friendName => this.removeFromFriendsList(friendName, p.name))
      this.socialNetwork.remove(p.name)
    })
  }

  private def addFriendsList(personName: String, personToBeFriended: String): Unit = {
    val person: Option[Person] = this.socialNetwork.get(personName)
    person.foreach(p => p.addFriend(personToBeFriended))
  }

  private def removeFromFriendsList(personName: String, personToBeUnfriended: String): Unit = {
      val person: Option[Person] = this.socialNetwork.get(personName)
      person.foreach(p => p.removeFriend(personToBeUnfriended))
  }

  def addFriend(personName: String, friendName: String): Unit = {
    addFriendsList(personName, friendName)
    addFriendsList(friendName, personName)
  }

  def removeFriend(personName: String, friendName: String): Unit = {
    removeFromFriendsList(personName, friendName)
    removeFromFriendsList(friendName, personName)
  }

  def getFriendsCountOf(personName: String) : Try[Int] ={
    Try(this.socialNetwork(personName).friendList.size)
  }

  def getPersonWithMostFriends: String= {
    this.socialNetwork.maxBy(p => p._2.friendList.size)._1
  }

  def getPeopleWithNoFriends: Iterable[String] = {
    this.socialNetwork.filter(p => p._2.friendList.isEmpty).map(p => p._1)
  }

  def socialConnectionExists(person1: String, person2: String): Boolean = ???
//  @tailrec
//  def bfs(target: String, consideredPeople: Set[String],
  //  discoveredPeople: Set[String]): Boolean = {
//    if (discoveredPeople.isEmpty) false
//    else {
//      val person = discoveredPeople.head
//      if (person == target) true
//      else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
//      else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
//    }
//  }
//
//  bfs(b, Set(), network(a) + a)
}
