package engine.lobby.example

import engine.ServerToClientLobbyExample

case class AvatarInfo(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = AvatarInfo.`type`
}

object AvatarInfo {
  val `type`: String = "AvatarInfo"
}
