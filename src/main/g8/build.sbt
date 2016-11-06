lazy val basicSettings = Seq(
  organization := "$organization$",
  name := "$name$",
  version := "$version$",
  scalaVersion := "2.11.8"
)

lazy val client = project
  .settings(basicSettings)
  .dependsOn(sharedJS)
  .enablePlugins(ScalaJSPlugin, ScalaJSWeb)
  .settings(
    persistLauncher := true,
    persistLauncher in Test := false,
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.1"
    )
  )

lazy val http4sV = "0.14.10"
lazy val server = project
  .settings(basicSettings)
  .settings(serverBuildSettings)
  .dependsOn(sharedJVM)
  .enablePlugins(SbtWeb)
  .settings(
    scalaJSProjects := Seq(client),
    pipelineStages in Assets := Seq(scalaJSPipeline),
    compile in Compile := (compile in Compile dependsOn scalaJSPipeline).value,
    WebKeys.packagePrefix in Assets := "public/",
    managedClasspath in Runtime += (packageBin in Assets).value,
    libraryDependencies ++= Seq(
      "com.vmunier" %% "scalajs-scripts"     % "1.0.0",
      "org.http4s"  %% "http4s-dsl"          % http4sV,
      "org.http4s"  %% "http4s-blaze-server" % http4sV,
      "org.http4s"  %% "http4s-blaze-client" % http4sV
    )
  )

lazy val scalatagsV = "0.5.4"
lazy val upickleV   = "0.4.1"
lazy val autowireV  = "0.2.5"
lazy val shared = crossProject
  .crossType(CrossType.Pure)
  .settings(basicSettings: _*)
  .jsConfigure(_ enablePlugins ScalaJSWeb)
  .settings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "upickle"   % upickleV,
      "com.lihaoyi" %%% "autowire"  % autowireV,
      "com.lihaoyi" %%% "scalatags" % scalatagsV
    )
  )

lazy val sharedJS = shared.js

lazy val sharedJVM = shared.jvm

lazy val serverBuildSettings = Seq(
  javacOptions ++= Seq("-source", "1.7", "-target", "1.7"),
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding",
    "UTF-8",
    "-feature",
    "-language:implicitConversions",
    "-language:existentials",
    "-language:higherKinds",
    "-language:postfixOps",
    "-unchecked",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Xfuture",
    "-target:jvm-1.7"
  )
)

// loads the server project at sbt startup
onLoad in Global := (Command.process("project server", _: State)) compose (onLoad in Global).value
