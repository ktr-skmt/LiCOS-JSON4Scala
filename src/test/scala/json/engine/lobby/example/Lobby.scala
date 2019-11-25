package json.engine.lobby.example

import json.engine.ServerToClientLobbyExample

final case class Lobby(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = Lobby.`type`
}

object Lobby {
  val `type`: String = "Lobby"
}
