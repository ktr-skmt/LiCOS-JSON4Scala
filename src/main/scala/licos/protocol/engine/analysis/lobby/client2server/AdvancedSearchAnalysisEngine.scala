package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.AdvancedSearchProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait AdvancedSearchAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, advancedSearchProtocol: AdvancedSearchProtocol): Try[LobbyMessageProtocol]
}

object AdvancedSearchAnalysisEngine {
  val name: String = "lobby.client2server.AdvancedSearch"
}
