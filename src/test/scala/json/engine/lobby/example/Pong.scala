package json.engine.lobby.example

import json.engine.ClientToServerLobbyExample

case class Pong(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = Pong.`type`
}

object Pong {
  val `type`: String = "Pong"
}
