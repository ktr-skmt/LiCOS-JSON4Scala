package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait EnterLobbyAnalysisEngine extends LobbyMessageAnalysisEngine

object EnterLobbyAnalysisEngine {
  val name: String = "lobby.client2server.EnterLobby"
}
