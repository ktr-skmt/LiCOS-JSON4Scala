package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class IdSearch(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = IdSearch.`type`
}

object IdSearch {
  val `type`: String = "lobby.client2server.IdSearch"
}
