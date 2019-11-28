package protocol.engine.lobby.example

import licos.json.element.lobby.client2server.JsonChangeUserEmail
import protocol.engine.ClientToServerLobbyExample

final case class ChangeUserEmail(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = JsonChangeUserEmail.`type`
}
