package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait SelectVillageAnalysisEngine extends LobbyMessageAnalysisEngine

object SelectVillageAnalysisEngine {
  val name: String = "lobby.client2server.SelectVillage"
}