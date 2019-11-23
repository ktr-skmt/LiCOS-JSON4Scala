package json.engine.lobby.example

import json.engine.ClientToServerLobbyExample

case class ChangeUserEmail(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = ChangeUserEmail.`type`
}

object ChangeUserEmail {
  val `type`: String = "ChangeUserEmail"
}