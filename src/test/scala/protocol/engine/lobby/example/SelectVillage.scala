package protocol.engine.lobby.example

import licos.json.element.lobby.client2server.JsonSelectVillage
import protocol.engine.ClientToServerLobbyExample

final case class SelectVillage(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = JsonSelectVillage.`type`
}
