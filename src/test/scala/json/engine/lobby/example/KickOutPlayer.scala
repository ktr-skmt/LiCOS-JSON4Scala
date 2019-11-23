package json.engine.lobby.example

import json.engine.ClientToServerLobbyExample

case class KickOutPlayer(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = KickOutPlayer.`type`
}

object KickOutPlayer {
  val `type`: String = "KickOutPlayer"
}