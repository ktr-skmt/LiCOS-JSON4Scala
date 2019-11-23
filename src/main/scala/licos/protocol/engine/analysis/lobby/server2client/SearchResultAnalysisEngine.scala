package licos.protocol.engine.analysis.lobby.server2client

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait SearchResultAnalysisEngine extends LobbyMessageAnalysisEngine

object SearchResultAnalysisEngine {
  val name: String = "lobby.server2client.SearchResult"
}
