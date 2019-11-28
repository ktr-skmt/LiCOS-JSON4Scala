package protocol.engine.lobby.example

import licos.json.element.lobby.server2client.JsonSettings
import protocol.engine.ServerToClientLobbyExample

final case class Settings(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = JsonSettings.`type`
}
