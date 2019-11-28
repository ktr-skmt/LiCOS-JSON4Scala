package protocol.engine.lobby.example

import licos.json.element.lobby.server2client.JsonPlayed
import protocol.engine.ServerToClientLobbyExample

final case class Played(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = JsonPlayed.`type`
}
