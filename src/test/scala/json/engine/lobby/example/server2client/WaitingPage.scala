package json.engine.lobby.example.server2client

import json.engine.ServerToClientLobbyExample

final case class WaitingPage(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = WaitingPage.`type`
}

object WaitingPage {
  val `type`: String = "WaitingPage"
}
