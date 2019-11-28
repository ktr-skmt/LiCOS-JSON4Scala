package protocol.engine.lobby.example

import licos.json.element.lobby.client2server.JsonReady
import protocol.engine.ClientToServerLobbyExample

final case class Ready(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = JsonReady.`type`
}
