package licos.protocol.engine.analysis.lobby.server2client

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait PingAnalysisEngine extends LobbyMessageAnalysisEngine

object PingAnalysisEngine {
  val name: String = "lobby.server2client.Ping"
}