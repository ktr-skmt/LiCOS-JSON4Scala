package engine.lobby.example

import engine.ClientToServerLobbyExample

case class LeaveWaitingPage(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = LeaveWaitingPage.`type`
}

object LeaveWaitingPage {
  val `type`: String = "LeaveWaitingPage"
}
