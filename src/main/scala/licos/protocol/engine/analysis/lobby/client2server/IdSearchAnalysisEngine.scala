package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait IdSearchAnalysisEngine extends LobbyMessageAnalysisEngine

object IdSearchAnalysisEngine {
  val name: String = "lobby.client2server.IdSearch"
}
