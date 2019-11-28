package protocol.engine.lobby.example

import licos.json.element.lobby.client2server.JsonBuildVillage
import protocol.engine.ClientToServerLobbyExample

final case class BuildVillage(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = JsonBuildVillage.`type`
}
