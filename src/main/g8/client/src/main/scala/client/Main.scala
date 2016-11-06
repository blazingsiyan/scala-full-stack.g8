package client

import org.scalajs.dom
import scala.scalajs.js.JSApp
import scalatags.JsDom.all._

object Main extends JSApp {
  def main(): Unit = {
    dom.document.body.appendChild(
      div(
        h1("It works!")
      ).render
    )
  }
}
