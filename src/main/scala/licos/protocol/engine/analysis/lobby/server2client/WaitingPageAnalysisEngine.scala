package licos.protocol.engine.analysis.lobby.server2client

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait WaitingPageAnalysisEngine extends LobbyMessageAnalysisEngine

object WaitingPageAnalysisEngine {
  val name: String = "lobby.server2client.WaitingPage"
}
