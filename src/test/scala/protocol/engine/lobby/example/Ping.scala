package protocol.engine.lobby.example

import licos.json.element.lobby.server2client.JsonPing
import protocol.engine.ServerToClientLobbyExample

final case class Ping(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = JsonPing.`type`
}
