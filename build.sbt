import Dependencies._

organization := "org.scala.learning"

name := "scala-learning"

val appVersion = "1.0.0"

val appName = "Scala Learning"

version := appVersion

scalaVersion := "2.12.10"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalacheck" % "scalacheck_2.12" % "1.14.2",
  scalaTest % Test
)
