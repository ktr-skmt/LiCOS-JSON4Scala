package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ReadyProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX

import scala.util.Try

trait ReadyAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, readyProtocol: ReadyProtocol): Try[LobbyMessageProtocol]
}

object ReadyAnalysisEngine {
  val name: String = "lobby.client2server.Ready"
}
