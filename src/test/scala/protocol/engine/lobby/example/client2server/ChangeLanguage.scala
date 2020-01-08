package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class ChangeLanguage(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = ChangeLanguage.`type`
}

object ChangeLanguage {
  val `type`: String = "lobby.client2server.ChangeLanguage"
}
