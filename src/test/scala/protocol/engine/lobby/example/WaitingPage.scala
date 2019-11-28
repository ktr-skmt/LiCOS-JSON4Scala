package protocol.engine.lobby.example

import licos.json.element.lobby.server2client.JsonWaitingPage
import protocol.engine.ServerToClientLobbyExample

final case class WaitingPage(filePath: String) extends ServerToClientLobbyExample(filePath) {
  override val `type`: String = JsonWaitingPage.`type`
}
