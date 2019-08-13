package engine.lobby.example

import engine.ClientToServerLobbyExample

case class GetSettings(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = GetSettings.`type`
}

object GetSettings {
  val `type`: String = "GetSettings"
}