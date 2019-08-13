package engine.lobby.example

import engine.ClientToServerLobbyExample

case class ChangeUserName(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = ChangeUserName.`type`
}

object ChangeUserName {
  val `type`: String = "ChangeUserName"
}