package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.BuildVillageProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait BuildVillageAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, buildVillageProtocol: BuildVillageProtocol): Try[LobbyMessageProtocol]
}

object BuildVillageAnalysisEngine {
  val name: String = "lobby.client2server.BuildVillage"
}
