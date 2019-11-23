package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait GetAvatarInfoAnalysisEngine extends LobbyMessageAnalysisEngine

object GetAvatarInfoAnalysisEngine {
  val name: String = "lobby.client2server."
}