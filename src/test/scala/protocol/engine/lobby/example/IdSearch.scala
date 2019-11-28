package protocol.engine.lobby.example

import licos.json.element.lobby.client2server.JsonIdSearch
import protocol.engine.ClientToServerLobbyExample

final case class IdSearch(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = JsonIdSearch.`type`
}
