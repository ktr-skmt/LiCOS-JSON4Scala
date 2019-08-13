package engine.lobby.example

import engine.ClientToServerLobbyExample

case class AdvancedSearch(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = AdvancedSearch.`type`
}

object AdvancedSearch {
  val `type`: String = "AdvancedSearch"
}
