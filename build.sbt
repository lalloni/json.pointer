scalaVersion := "2.10.2"

scalacOptions ++= Seq("-deprecation")

libraryDependencies += "org.parboiled" %% "parboiled-scala" % "1.1.5"

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.1.0-M2"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.0.M6" % "test"
