package protocol.engine.lobby.example

import licos.json.element.lobby.server2client.JsonSearchResult
import protocol.engine.ServerToClientLobbyExample

final case class SearchResult(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = JsonSearchResult.`type`
}
