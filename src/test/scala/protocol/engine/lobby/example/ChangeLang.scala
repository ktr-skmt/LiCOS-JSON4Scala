package protocol.engine.lobby.example

import licos.json.element.lobby.client2server.JsonChangeLang
import protocol.engine.ClientToServerLobbyExample

final case class ChangeLang(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = JsonChangeLang.`type`
}
