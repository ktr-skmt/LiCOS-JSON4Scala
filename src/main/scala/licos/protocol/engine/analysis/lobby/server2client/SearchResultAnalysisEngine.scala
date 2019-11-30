package licos.protocol.engine.analysis.lobby.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.SearchResultProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait SearchResultAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, searchResultProtocol: SearchResultProtocol): Try[LobbyMessageProtocol]
}

object SearchResultAnalysisEngine {
  val name: String = "lobby.server2client.SearchResult"
}
