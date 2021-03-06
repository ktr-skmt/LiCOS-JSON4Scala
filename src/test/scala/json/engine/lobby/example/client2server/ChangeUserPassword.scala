package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class ChangeUserPassword(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = ChangeUserPassword.`type`
}

object ChangeUserPassword {
  val `type`: String = "ChangeUserPassword"
}
