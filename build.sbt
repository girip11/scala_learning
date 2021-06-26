import Dependencies._

organization := "org.scala.learning"

name := "scala-learning"

val appVersion = "1.0.0"

val appName = "Scala Learning"

version := appVersion

scalaVersion := "2.12.10"

resolvers += Classpaths.typesafeReleases

// sbt update to pull newly added dependency
libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.6.1",
  "org.scalaz" %% "scalaz-core" % "7.3.3",
  scalaTest % Test
)
