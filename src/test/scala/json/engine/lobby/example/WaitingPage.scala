package json.engine.lobby.example

import json.engine.ServerToClientLobbyExample

final case class WaitingPage(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = WaitingPage.`type`
}

object WaitingPage {
  val `type`: String = "WaitingPage"
}
