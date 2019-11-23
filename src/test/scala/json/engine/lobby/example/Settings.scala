package json.engine.lobby.example

import json.engine.ServerToClientLobbyExample

case class Settings(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = Settings.`type`
}

object Settings {
  val `type`: String = "Settings"
}