package protocol.engine.lobby.example

import licos.json.element.lobby.client2server.JsonPlay
import protocol.engine.ClientToServerLobbyExample

final case class Play(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = JsonPlay.`type`
}
