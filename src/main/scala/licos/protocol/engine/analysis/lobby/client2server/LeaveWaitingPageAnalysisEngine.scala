package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait LeaveWaitingPageAnalysisEngine extends LobbyMessageAnalysisEngine

object LeaveWaitingPageAnalysisEngine {
  val name: String = "lobby.client2server.LeaveWaitingPage"
}