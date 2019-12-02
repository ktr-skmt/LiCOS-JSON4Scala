package protocol.engine.lobby.example.server2client

import protocol.engine.ServerToClientLobbyExample

final case class Settings(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = Settings.`type`
}

object Settings {
  val `type`: String = "lobby.server2client.Settings"
}
