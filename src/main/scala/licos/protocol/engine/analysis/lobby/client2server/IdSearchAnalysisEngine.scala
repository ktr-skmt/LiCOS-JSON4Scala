package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.IdSearchProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait IdSearchAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, idSearchProtocol: IdSearchProtocol): Try[LobbyMessageProtocol]
}

object IdSearchAnalysisEngine {
  val name: String = "lobby.client2server.IdSearch"
}
