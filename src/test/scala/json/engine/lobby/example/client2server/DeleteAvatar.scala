package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class DeleteAvatar(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = DeleteAvatar.`type`
}

object DeleteAvatar {
  val `type`: String = "DeleteAvatar"
}
