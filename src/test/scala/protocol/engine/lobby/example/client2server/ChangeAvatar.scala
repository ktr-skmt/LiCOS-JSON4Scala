package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class ChangeAvatar(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = ChangeAvatar.`type`
}

object ChangeAvatar {
  val `type`: String = "lobby.client2server.ChangeAvatar"
}
