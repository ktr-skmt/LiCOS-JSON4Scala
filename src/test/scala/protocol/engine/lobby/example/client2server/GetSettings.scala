package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class GetSettings(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = GetSettings.`type`
}

object GetSettings {
  val `type`: String = "lobby.client2server.GetSettings"
}
