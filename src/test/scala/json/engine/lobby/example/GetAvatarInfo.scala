package json.engine.lobby.example

import json.engine.ClientToServerLobbyExample

final case class GetAvatarInfo(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = GetAvatarInfo.`type`
}

object GetAvatarInfo {
  val `type`: String = "GetAvatarInfo"
}
