package org.scala.scalaImpatient

// scalastyle:off regex.println
object Chapter13 extends App {

  //11.
  "Mississippi".par.aggregate(scala.collection.mutable.Map[Char, Int]())(
    (m,c) => {
      m.getOrElseUpdate(c,0)
      m(c) += 1
      m
    }, (m1, m2) => {
      m2.foreach(e => {
        m1.getOrElseUpdate(e._1, 0)
        m1(e._1) += e._2
      })
      m1
    }).foreach(println)
}
