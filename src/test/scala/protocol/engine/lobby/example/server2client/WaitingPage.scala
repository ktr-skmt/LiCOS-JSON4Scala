package protocol.engine.lobby.example.server2client

import protocol.engine.ServerToClientLobbyExample

final case class WaitingPage(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = WaitingPage.`type`
}

object WaitingPage {
  val `type`: String = "lobby.server2client.WaitingPage"
}
