package json.engine.lobby.example.server2server

import json.engine.ServerToServerLobbyExample

final case class PlayedWithToken(filePath: String) extends ServerToServerLobbyExample(filePath) {
  override val `type`: String = PlayedWithToken.`type`
}

object PlayedWithToken {
  val `type`: String = "PlayedWithToken"
}
