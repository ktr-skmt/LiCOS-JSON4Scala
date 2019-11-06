package engine.lobby.example

import engine.ClientToServerLobbyExample

case class EnterLobby(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = EnterLobby.`type`
}

object EnterLobby {
  val `type`: String = "EnterLobby"
}
