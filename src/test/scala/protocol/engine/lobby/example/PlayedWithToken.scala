package protocol.engine.lobby.example

import licos.json.element.lobby.server2server.JsonPlayedWithToken
import protocol.engine.ServerToServerLobbyExample

final case class PlayedWithToken(filePath: String) extends ServerToServerLobbyExample(filePath) {
  override val `type`: String = JsonPlayedWithToken.`type`
}
