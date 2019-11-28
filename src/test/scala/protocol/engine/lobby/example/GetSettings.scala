package protocol.engine.lobby.example

import licos.json.element.lobby.client2server.JsonGetSettings
import protocol.engine.ClientToServerLobbyExample

final case class GetSettings(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = JsonGetSettings.`type`
}
