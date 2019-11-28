package protocol.engine.lobby.example

import licos.json.element.lobby.server2client.JsonGetAvatarInfo
import protocol.engine.ClientToServerLobbyExample

final case class GetAvatarInfo(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = JsonGetAvatarInfo.`type`
}
