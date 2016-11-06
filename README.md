My [Giter8][g8] template for scala full stack project, WIP.

## Usage

```sh
sbt new xsy233/scala-full-stack.g8
```

## Intro

Using scala.js for client, http4s for server, and autowire for communication.

- with sbt 0.13.13 and scala 2.11.8
- bundled with sbt, and sbt-launch.jar
- add a `repositories` file, for overriding global resolvers
- essential plugins
  - [coursier][coursier] for faster artifact fetching
  - [sbt-assembly][sbt-assembly] for packaging
  - [sbt-aether-deploy][sbt-aether-deploy] for better maven deploy support
  - [scala-js][scala-js] for scala.js support
  - [sbt-revolver][sbt-revolver] for sbt ~re-start feature
  - [sbt-web-scalajs][sbt-web-scalajs] for better client/server integration.

## Project Structure

```sh
root
|---client
|   |---src/main/scala/client/Main.scala
|---server
|   |---src/main/scala/server/Main.scala
|---shared
|   |---src/main/scala/Shared.scala
```

[g8]: http://www.foundweekends.org/giter8/
[coursier]: https://github.com/alexarchambault/coursier
[sbt-assembly]: https://github.com/sbt/sbt-assembly
[sbt-aether-deploy]: https://github.com/arktekk/sbt-aether-deploy
[scala-js]: https://github.com/scala-js/scala-js
[sbt-revolver]: https://github.com/spray/sbt-revolver
[sbt-web-scalajs]: https://github.com/vmunier/sbt-web-scalajs
