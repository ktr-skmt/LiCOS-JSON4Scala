package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait ReadyAnalysisEngine extends LobbyMessageAnalysisEngine

object ReadyAnalysisEngine {
  val name: String = "lobby.client2server.Ready"
}
