package licos.protocol.engine.analysis.lobby.server2server

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait PlayedWithTokenAnalysisEngine extends LobbyMessageAnalysisEngine

object PlayedWithTokenAnalysisEngine {
  val name: String = "lobby.server2server.PlayedWithToken"
}