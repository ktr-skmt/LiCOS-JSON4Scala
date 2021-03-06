package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class ChangeUserEmail(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = ChangeUserEmail.`type`
}

object ChangeUserEmail {
  val `type`: String = "ChangeUserEmail"
}
