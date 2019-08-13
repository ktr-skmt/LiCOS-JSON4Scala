package engine.lobby.example

import engine.ClientToServerLobbyExample

case class KickOutPlayer(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = KickOutPlayer.`type`
}

object KickOutPlayer {
  val `type`: String = "KickOutPlayer"
}