package protocol.engine.lobby.example.server2client

import protocol.engine.ServerToClientLobbyExample

final case class AvatarInfo(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = AvatarInfo.`type`
}

object AvatarInfo {
  val `type`: String = "lobby.server2client.AvatarInfo"
}
