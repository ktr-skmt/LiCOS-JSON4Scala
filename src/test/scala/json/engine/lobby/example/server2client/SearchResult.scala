package json.engine.lobby.example.server2client

import json.engine.ServerToClientLobbyExample

final case class SearchResult(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = SearchResult.`type`
}

object SearchResult {
  val `type`: String = "SearchResult"
}
