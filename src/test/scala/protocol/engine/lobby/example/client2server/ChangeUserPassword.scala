package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class ChangeUserPassword(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = ChangeUserPassword.`type`
}

object ChangeUserPassword {
  val `type`: String = "lobby.client2server.ChangeUserPassword"
}
