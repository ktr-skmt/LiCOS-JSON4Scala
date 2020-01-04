package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.SelectHumanPlayerProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait SelectHumanPlayerAnalysisEngine {
  def process(box: LobbyBOX, selectHumanPlayerProtocol: SelectHumanPlayerProtocol): Try[LobbyMessageProtocol]
}

object SelectHumanPlayerAnalysisEngine {
  val name: String = "lobby.client2server.SelectHumanPlayer"
}
