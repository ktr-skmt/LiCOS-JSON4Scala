package protocol.engine.lobby.example

import licos.json.element.lobby.server2client.JsonAvatarInfo
import protocol.engine.ServerToClientLobbyExample

final case class AvatarInfo(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = JsonAvatarInfo.`type`
}
