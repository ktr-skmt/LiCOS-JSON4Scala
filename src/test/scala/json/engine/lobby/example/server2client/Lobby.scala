package json.engine.lobby.example.server2client

import json.engine.ServerToClientLobbyExample

final case class Lobby(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = Lobby.`type`
}

object Lobby {
  val `type`: String = "Lobby"
}
