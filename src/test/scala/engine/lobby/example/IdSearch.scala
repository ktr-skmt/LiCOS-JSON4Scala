package engine.lobby.example

import engine.ClientToServerLobbyExample

case class IdSearch(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = IdSearch.`type`
}

object IdSearch {
  val `type`: String = "IdSearch"
}