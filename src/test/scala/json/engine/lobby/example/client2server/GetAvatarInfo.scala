package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class GetAvatarInfo(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = GetAvatarInfo.`type`
}

object GetAvatarInfo {
  val `type`: String = "GetAvatarInfo"
}
