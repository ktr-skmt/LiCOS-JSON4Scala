package engine.lobby.example

import engine.ClientToServerLobbyExample

case class GetAvatarInfo(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = GetAvatarInfo.`type`
}

object GetAvatarInfo {
  val `type`: String = "GetAvatarInfo"
}