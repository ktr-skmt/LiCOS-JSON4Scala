package protocol.engine.lobby.example

import licos.json.element.lobby.client2server.JsonPong
import protocol.engine.ClientToServerLobbyExample

final case class Pong(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = JsonPong.`type`
}
