package server

import org.http4s._
import org.http4s.dsl._
import org.http4s.server.blaze._
import org.http4s.server.{Server, ServerApp}

import scalaz.concurrent.Task

object Html {
  import scalatags.Text.all._
  import scalatags.Text.tags2.title

  val index = "<!DOCTYPE html>" +
      html(
        head(
          title("Example Scala.js application"),
          meta(httpEquiv := "Content-Type", content := "text/html; charset=UTF-8"),
          script(`type` := "text/javascript", src := "public/$name$-fastopt.js")
        ),
        body(margin := 0)(
          script("client.Main().main()")
        )
      )
}

object Main extends ServerApp {
  val indexService = HttpService {
    case req @ GET -> Root =>
      Ok(Html.index).withContentType(Some(MediaType.`text/html`))

    case req @ GET -> "public" /: path =>
      val file = s"/public\${path.toString}"
      StaticFile.fromResource(file, Some(req)).fold(NotFound())(Task.now)
  }

  val services = indexService

  def server(args: List[String]): Task[Server] = {
    BlazeBuilder.bindHttp(12306, "localhost").mountService(services, "/").start
  }
}
