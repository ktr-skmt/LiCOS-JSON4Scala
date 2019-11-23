package json.engine.lobby.example

import json.engine.ClientToServerLobbyExample

case class IdSearch(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = IdSearch.`type`
}

object IdSearch {
  val `type`: String = "IdSearch"
}
