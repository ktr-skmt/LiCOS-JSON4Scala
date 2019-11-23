package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait PlayAnalysisEngine extends LobbyMessageAnalysisEngine

object PlayAnalysisEngine {
  val name: String = "lobby.client2server.Play"
}