name := "freightTrain"

version := "0.1"

scalaVersion := "2.12.6"

maintainer := "Valere Tetelin <valere.tetelin@gmail.com>"

packageSummary := "FreightTrain is a simple docker linking utility"
packageDescription := "todo"

libraryDependencies += "org.rogach" %% "scallop" % "3.1.3"
libraryDependencies += "io.circe" %% "circe-yaml" % "0.8.0"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.7"

enablePlugins(JavaAppPackaging)
enablePlugins(DebianPlugin)

debianPackageDependencies := Seq("java8-runtime-headless")
