package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait PongAnalysisEngine extends LobbyMessageAnalysisEngine

object PongAnalysisEngine {
  val name: String = "lobby.client2server.Pong"
}