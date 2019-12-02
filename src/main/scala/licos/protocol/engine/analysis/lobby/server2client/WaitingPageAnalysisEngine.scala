package licos.protocol.engine.analysis.lobby.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.WaitingPageProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait WaitingPageAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, waitingPageProtocol: WaitingPageProtocol): Try[LobbyMessageProtocol]
}

object WaitingPageAnalysisEngine {
  val name: String = "lobby.server2client.WaitingPage"
}
