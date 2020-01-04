package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class SelectHumanPlayer(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = SelectHumanPlayer.`type`
}

object SelectHumanPlayer {
  val `type`: String = "SelectHumanPlayer"
}
