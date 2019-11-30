package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.SelectVillageProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait SelectVillageAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, selectVillageProtocol: SelectVillageProtocol): Try[LobbyMessageProtocol]
}

object SelectVillageAnalysisEngine {
  val name: String = "lobby.client2server.SelectVillage"
}
