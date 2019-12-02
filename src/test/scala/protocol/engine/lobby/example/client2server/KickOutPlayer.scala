package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class KickOutPlayer(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = KickOutPlayer.`type`
}

object KickOutPlayer {
  val `type`: String = "lobby.client2server.KickOutPlayer"
}
