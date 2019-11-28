package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class AdvancedSearch(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = AdvancedSearch.`type`
}

object AdvancedSearch {
  val `type`: String = "AdvancedSearch"
}
