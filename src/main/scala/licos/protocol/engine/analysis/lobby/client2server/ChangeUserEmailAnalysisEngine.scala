package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait ChangeUserEmailAnalysisEngine extends LobbyMessageAnalysisEngine

object ChangeUserEmailAnalysisEngine {
  val name: String = "lobby.client2server.ChangeUserEmail"
}
