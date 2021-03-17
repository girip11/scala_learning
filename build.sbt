import Dependencies._

organization := "org.scala.learning"

name := "scala-learning"

val appVersion = "1.0.0"

val appName = "Scala Learning"

version := appVersion

scalaVersion := "2.12.10"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  scalaTest % Test
)
