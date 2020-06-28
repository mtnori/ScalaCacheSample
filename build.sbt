import Dependencies._

ThisBuild / scalaVersion := "2.12.10"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "ScalaCacheExample",
    libraryDependencies += scalaTest % Test
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
libraryDependencies += "com.github.blemale" %% "scaffeine" % "3.1.0" % "compile"
libraryDependencies += "com.github.etaty" %% "rediscala" % "1.8.0"
libraryDependencies += "io.circe" %% "circe-core" % "0.13.0"
libraryDependencies += "io.circe" %% "circe-parser" % "0.13.0"
libraryDependencies += "io.circe" %% "circe-generic" % "0.13.0"
