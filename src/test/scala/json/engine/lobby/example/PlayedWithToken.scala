package json.engine.lobby.example

import json.engine.ServerToServerLobbyExample

case class PlayedWithToken(filePath: String) extends ServerToServerLobbyExample(filePath) {
  override val `type`: String = PlayedWithToken.`type`
}

object PlayedWithToken {
  val `type`: String = "PlayedWithToken"
}
