package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class Pong(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = Pong.`type`
}

object Pong {
  val `type`: String = "lobby.client2server.Pong"
}
