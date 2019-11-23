package licos.protocol.engine.analysis.lobby.server2client

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait LobbyAnalysisEngine extends LobbyMessageAnalysisEngine

object LobbyAnalysisEngine {
  val name: String = "lobby.server2client.Lobby"
}