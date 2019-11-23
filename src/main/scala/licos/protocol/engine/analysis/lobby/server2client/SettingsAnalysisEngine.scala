package licos.protocol.engine.analysis.lobby.server2client

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait SettingsAnalysisEngine extends LobbyMessageAnalysisEngine

object SettingsAnalysisEngine {
  val name: String = "lobby.server2client.Settings"
}