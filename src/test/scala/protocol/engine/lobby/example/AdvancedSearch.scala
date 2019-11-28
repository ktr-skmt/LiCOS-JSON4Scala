package protocol.engine.lobby.example

import licos.json.element.lobby.client2server.JsonAdvancedSearch
import protocol.engine.ClientToServerLobbyExample

final case class AdvancedSearch(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = JsonAdvancedSearch.`type`
}
