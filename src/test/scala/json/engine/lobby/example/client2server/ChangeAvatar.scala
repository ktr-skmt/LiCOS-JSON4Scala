package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class ChangeAvatar(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = ChangeAvatar.`type`
}

object ChangeAvatar {
  val `type`: String = "ChangeAvatar"
}
