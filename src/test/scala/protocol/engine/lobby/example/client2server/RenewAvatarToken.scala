package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class RenewAvatarToken(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = RenewAvatarToken.`type`
}

object RenewAvatarToken {
  val `type`: String = "lobby.client2server.RenewAvatarToken"
}
