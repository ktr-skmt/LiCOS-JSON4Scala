package json.engine.lobby.example

import json.engine.ServerToClientLobbyExample

final case class Settings(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = Settings.`type`
}

object Settings {
  val `type`: String = "Settings"
}
