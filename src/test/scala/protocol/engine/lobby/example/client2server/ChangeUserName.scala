package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class ChangeUserName(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = ChangeUserName.`type`
}

object ChangeUserName {
  val `type`: String = "lobby.client2server.ChangeUserName"
}
