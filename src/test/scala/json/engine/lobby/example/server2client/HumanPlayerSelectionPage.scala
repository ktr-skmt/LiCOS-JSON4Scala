package json.engine.lobby.example.server2client

import json.engine.ServerToClientLobbyExample

final case class HumanPlayerSelectionPage(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = HumanPlayerSelectionPage.`type`
}

object HumanPlayerSelectionPage {
  val `type`: String = "HumanPlayerSelectionPage"
}
