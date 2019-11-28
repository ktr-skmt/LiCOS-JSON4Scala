package protocol.engine.lobby.example

import licos.json.element.lobby.client2server.JsonEnterLobby
import protocol.engine.ClientToServerLobbyExample

final case class EnterLobby(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = JsonEnterLobby.`type`
}
