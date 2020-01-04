package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class SelectOnymousAudience(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = SelectOnymousAudience.`type`
}

object SelectOnymousAudience {
  val `type`: String = "lobby.client2server.SelectOnymousAudience"
}
