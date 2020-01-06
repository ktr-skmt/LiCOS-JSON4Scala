package protocol.engine.lobby.example.server2client

import protocol.engine.ServerToClientLobbyExample

final case class OnymousAudienceSelectionPage(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = OnymousAudienceSelectionPage.`type`
}

object OnymousAudienceSelectionPage {
  val `type`: String = "lobby.server2client.OnymousAudienceSelectionPage"
}
