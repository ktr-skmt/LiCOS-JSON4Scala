package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class LeaveWaitingPage(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = LeaveWaitingPage.`type`
}

object LeaveWaitingPage {
  val `type`: String = "lobby.client2server.LeaveWaitingPage"
}
