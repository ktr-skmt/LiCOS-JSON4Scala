package protocol.engine.lobby.example.server2client

import protocol.engine.ServerToClientLobbyExample

final case class HumanPlayerSelectionPage(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = HumanPlayerSelectionPage.`type`
}

object HumanPlayerSelectionPage {
  val `type`: String = "lobby.server2client.HumanPlayerSelectionPage"
}
