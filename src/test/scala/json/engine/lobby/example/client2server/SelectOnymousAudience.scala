package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class SelectOnymousAudience(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = SelectOnymousAudience.`type`
}

object SelectOnymousAudience {
  val `type`: String = "SelectOnymousAudience"
}
