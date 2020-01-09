package protocol.engine.lobby.example.server2client

import protocol.engine.ServerToClientLobbyExample

final case class RobotPlayerSelectionPage(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = RobotPlayerSelectionPage.`type`
}

object RobotPlayerSelectionPage {
  val `type`: String = "lobby.server2client.RobotPlayerSelectionPage"
}
