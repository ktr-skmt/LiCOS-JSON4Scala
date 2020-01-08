package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class EnterAvatarSelectionPage(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = EnterAvatarSelectionPage.`type`
}

object EnterAvatarSelectionPage {
  val `type`: String = "lobby.client2server.EnterAvatarSelectionPage"
}
