package json.engine.lobby.example.server2client

import json.engine.ServerToClientLobbyExample

final case class OnymousAudienceSelectionPage(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = OnymousAudienceSelectionPage.`type`
}

object OnymousAudienceSelectionPage {
  val `type`: String = "OnymousAudienceSelectionPage"
}
