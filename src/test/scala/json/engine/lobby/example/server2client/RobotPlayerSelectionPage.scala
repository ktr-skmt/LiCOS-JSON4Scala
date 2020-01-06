package json.engine.lobby.example.server2client

import json.engine.ServerToClientLobbyExample

final case class RobotPlayerSelectionPage(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = RobotPlayerSelectionPage.`type`
}

object RobotPlayerSelectionPage {
  val `type`: String = "RobotPlayerSelectionPage"
}
