package licos.protocol.engine.analysis.lobby.server2client

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait PlayedAnalysisEngine extends LobbyMessageAnalysisEngine

object PlayedAnalysisEngine {
  val name: String = "lobby.server2client.Played"
}