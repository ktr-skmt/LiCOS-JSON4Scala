package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait BuildVillageAnalysisEngine extends LobbyMessageAnalysisEngine

object BuildVillageAnalysisEngine {
  val name: String = "lobby.client2server.BuildVillage"
}