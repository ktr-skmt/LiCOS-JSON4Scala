package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class ChangeUserName(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = ChangeUserName.`type`
}

object ChangeUserName {
  val `type`: String = "ChangeUserName"
}
