package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class KickOutPlayer(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = KickOutPlayer.`type`
}

object KickOutPlayer {
  val `type`: String = "KickOutPlayer"
}
