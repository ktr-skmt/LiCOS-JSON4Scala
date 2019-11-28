package protocol.engine.lobby.example.server2server

import protocol.engine.ServerToServerLobbyExample

final case class PlayedWithToken(filePath: String) extends ServerToServerLobbyExample(filePath) {
  override val `type`: String = PlayedWithToken.`type`
}

object PlayedWithToken {
  val `type`: String = "lobby.server2server.PlayedWithToken"
}
