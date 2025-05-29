ThisBuild / version := "1.0.0"

ThisBuild / scalaVersion := "3.3.5"

lazy val root = (project in file("."))
        .settings(
            name := "pca.problema2"
        )

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.8.8"
