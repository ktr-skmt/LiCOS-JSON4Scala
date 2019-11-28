package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class Play(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = Play.`type`
}

object Play {
  val `type`: String = "lobby.client2server.Play"
}
