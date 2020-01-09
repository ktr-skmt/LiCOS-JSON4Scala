package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class UpdateAvatar(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = UpdateAvatar.`type`
}

object UpdateAvatar {
  val `type`: String = "lobby.client2server.UpdateAvatar"
}
