package json.engine.lobby.example

import json.engine.ClientToServerLobbyExample

case class ChangeUserPassword(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = ChangeUserPassword.`type`
}

object ChangeUserPassword {
  val `type`: String = "ChangeUserPassword"
}