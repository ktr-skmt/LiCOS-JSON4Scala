package json.engine.lobby.example

import json.engine.ServerToClientLobbyExample

case class SearchResult(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = SearchResult.`type`
}

object SearchResult {
  val `type`: String = "SearchResult"
}
