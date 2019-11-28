package protocol.engine.lobby.example

import licos.json.element.lobby.client2server.JsonLeaveWaitingPage
import protocol.engine.ClientToServerLobbyExample

final case class LeaveWaitingPage(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = JsonLeaveWaitingPage.`type`
}
