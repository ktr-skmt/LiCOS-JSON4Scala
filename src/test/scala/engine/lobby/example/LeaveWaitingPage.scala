package engine.lobby.example

import engine.Example

case class LeaveWaitingPage(filePath: String) extends Example(filePath) {
  override val `type`: String = LeaveWaitingPage.`type`
}

object LeaveWaitingPage {
  val `type`: String = "LeaveWaitingPage"
}
