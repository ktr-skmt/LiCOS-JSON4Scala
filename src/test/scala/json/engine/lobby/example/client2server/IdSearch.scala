package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class IdSearch(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = IdSearch.`type`
}

object IdSearch {
  val `type`: String = "IdSearch"
}
