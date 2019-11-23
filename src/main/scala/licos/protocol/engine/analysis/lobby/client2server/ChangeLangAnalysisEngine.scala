package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait ChangeLangAnalysisEngine extends LobbyMessageAnalysisEngine

object ChangeLangAnalysisEngine {
  val name: String = "lobby.client2server.ChangeLang"
}