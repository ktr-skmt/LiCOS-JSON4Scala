package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class AdvancedSearch(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = AdvancedSearch.`type`
}

object AdvancedSearch {
  val `type`: String = "lobby.client2server.AdvancedSearch"
}
