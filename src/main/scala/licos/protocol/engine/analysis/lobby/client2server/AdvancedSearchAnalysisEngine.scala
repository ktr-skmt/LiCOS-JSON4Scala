package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait AdvancedSearchAnalysisEngine extends LobbyMessageAnalysisEngine

object AdvancedSearchAnalysisEngine {
  val name: String = "lobby.client2server.AdvancedSearch"
}