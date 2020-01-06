package licos.protocol.engine.analysis.lobby.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.HumanPlayerSelectionPageProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait HumanPlayerSelectionPageAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(
      box:                              LobbyBOX,
      humanPlayerSelectionPageProtocol: HumanPlayerSelectionPageProtocol
  ): Try[LobbyMessageProtocol]
}

object HumanPlayerSelectionPageAnalysisEngine {
  val name: String = "lobby.server2client.HumanPlayerSelectionPage"
}
