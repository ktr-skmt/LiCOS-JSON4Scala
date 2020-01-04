package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class CreateHumanPlayer(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = CreateHumanPlayer.`type`
}

object CreateHumanPlayer {
  val `type`: String = "CreateHumanPlayer"
}
