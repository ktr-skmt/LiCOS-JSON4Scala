package licos.protocol.engine.analysis.lobby.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.RobotPlayerSelectionPageProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait RobotPlayerSelectionPageAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(
      box:                              LobbyBOX,
      robotPlayerSelectionPageProtocol: RobotPlayerSelectionPageProtocol
  ): Try[LobbyMessageProtocol]
}

object RobotPlayerSelectionPageAnalysisEngine {
  val name: String = "lobby.server2client.RobotPlayerSelectionPage"
}
