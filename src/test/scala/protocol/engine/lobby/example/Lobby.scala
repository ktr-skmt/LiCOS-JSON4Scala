package protocol.engine.lobby.example

import licos.json.element.lobby.server2client.JsonLobby
import protocol.engine.ServerToClientLobbyExample

final case class Lobby(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = JsonLobby.`type`
}
