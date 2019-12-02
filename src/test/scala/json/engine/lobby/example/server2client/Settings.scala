package json.engine.lobby.example.server2client

import json.engine.ServerToClientLobbyExample

final case class Settings(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = Settings.`type`
}

object Settings {
  val `type`: String = "Settings"
}
