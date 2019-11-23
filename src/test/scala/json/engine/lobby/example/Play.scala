package json.engine.lobby.example

import json.engine.ClientToServerLobbyExample

case class Play(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = Play.`type`
}

object Play {
  val `type`: String = "Play"
}
