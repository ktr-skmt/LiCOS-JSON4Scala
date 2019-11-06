package engine.lobby.example

import engine.ClientToServerLobbyExample

case class ChangeUserEmail(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = ChangeUserEmail.`type`
}

object ChangeUserEmail {
  val `type`: String = "ChangeUserEmail"
}
