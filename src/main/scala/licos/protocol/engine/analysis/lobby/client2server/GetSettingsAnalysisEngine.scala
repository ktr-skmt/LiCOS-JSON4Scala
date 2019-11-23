package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait GetSettingsAnalysisEngine extends LobbyMessageAnalysisEngine

object GetSettingsAnalysisEngine {
  val name: String = "lobby.client2server.GetSettings"
}
