package engine.lobby.example

import engine.Example

case class WaitingPage(filePath: String) extends Example(filePath) {
  override val `type`: String = WaitingPage.`type`
}

object WaitingPage {
  val `type`: String = "WaitingPage"
}