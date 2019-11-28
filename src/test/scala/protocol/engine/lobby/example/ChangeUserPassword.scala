package protocol.engine.lobby.example

import licos.json.element.lobby.client2server.JsonChangeUserPassword
import protocol.engine.ClientToServerLobbyExample

final case class ChangeUserPassword(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = JsonChangeUserPassword.`type`
}
