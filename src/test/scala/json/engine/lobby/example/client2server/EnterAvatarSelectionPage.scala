package json.engine.lobby.example.client2server

import json.engine.ClientToServerLobbyExample

final case class EnterAvatarSelectionPage(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = EnterAvatarSelectionPage.`type`
}

object EnterAvatarSelectionPage {
  val `type`: String = "EnterAvatarSelectionPage"
}
