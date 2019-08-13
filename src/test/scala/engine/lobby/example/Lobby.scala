package engine.lobby.example

import engine.ServerToClientLobbyExample

case class Lobby(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = Lobby.`type`
}

object Lobby {
  val `type`: String = "Lobby"
}