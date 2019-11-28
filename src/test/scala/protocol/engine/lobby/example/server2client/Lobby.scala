package protocol.engine.lobby.example.server2client

import protocol.engine.ServerToClientLobbyExample

final case class Lobby(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = Lobby.`type`
}

object Lobby {
  val `type`: String = "lobby.server2client.Lobby"
}
