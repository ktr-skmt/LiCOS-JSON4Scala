package licos.protocol.engine.analysis.lobby.server2client

import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine

trait AvatarInfoAnalysisEngine extends LobbyMessageAnalysisEngine

object AvatarInfoAnalysisEngine {
  val name: String = "lobby.server2client.AvatarInfo"
}