package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class ChangeUserEmail(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = ChangeUserEmail.`type`
}

object ChangeUserEmail {
  val `type`: String = "lobby.client2server.ChangeUserEmail"
}
