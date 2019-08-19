name := "TheAdvantageOfUsingEffInScalaProject"

version := "0.1"

scalaVersion := "2.12.3"
libraryDependencies +=
  "org.atnos" %% "eff" % "5.2.0"
scalacOptions ++= Seq(
  "-Xfatal-warnings",
  "-Ypartial-unification"
)