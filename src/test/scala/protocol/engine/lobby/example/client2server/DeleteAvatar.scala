package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class DeleteAvatar(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = DeleteAvatar.`type`
}

object DeleteAvatar {
  val `type`: String = "lobby.client2server.DeleteAvatar"
}
