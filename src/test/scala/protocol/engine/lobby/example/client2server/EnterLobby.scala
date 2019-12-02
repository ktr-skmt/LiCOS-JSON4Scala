package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class EnterLobby(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = EnterLobby.`type`
}

object EnterLobby {
  val `type`: String = "lobby.client2server.EnterLobby"
}
