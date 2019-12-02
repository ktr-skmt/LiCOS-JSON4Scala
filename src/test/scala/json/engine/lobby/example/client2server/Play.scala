package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class Play(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = Play.`type`
}

object Play {
  val `type`: String = "Play"
}
