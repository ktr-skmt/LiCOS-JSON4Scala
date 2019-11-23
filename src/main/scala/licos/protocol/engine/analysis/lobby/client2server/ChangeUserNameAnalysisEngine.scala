package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait ChangeUserNameAnalysisEngine extends LobbyMessageAnalysisEngine

object ChangeUserNameAnalysisEngine {
  val name: String = "lobby.client2server.ChangeUserName"
}
