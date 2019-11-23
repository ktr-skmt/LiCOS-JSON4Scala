package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait ChangeUserPasswordAnalysisEngine extends LobbyMessageAnalysisEngine

object ChangeUserPasswordAnalysisEngine {
  val name: String = "lobby.client2server.ChangeUserPassword"
}