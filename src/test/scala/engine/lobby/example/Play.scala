package engine.lobby.example

import engine.ClientToServerLobbyExample

case class Play(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = Play.`type`
}

object Play {
  val `type`: String = "Play"
}
