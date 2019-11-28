package protocol.engine.lobby.example

import licos.json.element.lobby.client2server.JsonChangeUserName
import protocol.engine.ClientToServerLobbyExample

final case class ChangeUserName(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = JsonChangeUserName.`type`
}
