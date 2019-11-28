package protocol.engine.lobby.example.server2client

import protocol.engine.ServerToClientLobbyExample

final case class SearchResult(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = SearchResult.`type`
}

object SearchResult {
  val `type`: String = "lobby.server2client.SearchResult"
}
