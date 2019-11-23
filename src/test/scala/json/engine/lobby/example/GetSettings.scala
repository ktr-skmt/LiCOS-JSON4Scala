package json.engine.lobby.example

import json.engine.ClientToServerLobbyExample

case class GetSettings(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = GetSettings.`type`
}

object GetSettings {
  val `type`: String = "GetSettings"
}
