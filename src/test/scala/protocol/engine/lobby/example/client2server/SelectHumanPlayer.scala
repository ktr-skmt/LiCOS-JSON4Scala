package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class SelectHumanPlayer(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = SelectHumanPlayer.`type`
}

object SelectHumanPlayer {
  val `type`: String = "lobby.client2server.SelectHumanPlayer"
}
