package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait KickOutPlayerAnalysisEngine extends LobbyMessageAnalysisEngine

object KickOutPlayerAnalysisEngine {
  val name: String = "lobby.client2server.KickOutPlayer"
}
