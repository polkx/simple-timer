val scalaVer = "2.12.13"
val paradiseVer = "2.1.1"
val scalaLoggingVer = "3.9.3"
val logbackClassicVer = "1.2.3"

val defaultSettings = Seq(
  organization := "com.github.polkx",
  version := "0.1.0",
  scalaVersion := scalaVer,
  addCompilerPlugin("org.scalamacros" % "paradise" % paradiseVer cross CrossVersion.full)
)

lazy val root: Project = Project("simple-timer", file("."))
  .aggregate(macros, core)
  .settings(defaultSettings)

lazy val macros: Project = project.in(file("macros"))
  .settings(defaultSettings,
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVer,
      "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVer
    )
  )

lazy val core: Project = project.in(file("core"))
  .settings(defaultSettings,
    libraryDependencies ++= Seq(
      "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVer,
      "ch.qos.logback" % "logback-classic" % logbackClassicVer
    )
  ).dependsOn(macros)