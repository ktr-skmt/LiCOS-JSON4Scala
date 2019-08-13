package engine.lobby.example

import engine.ClientToServerLobbyExample

case class Pong(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = Pong.`type`
}

object Pong {
  val `type`: String = "Pong"
}