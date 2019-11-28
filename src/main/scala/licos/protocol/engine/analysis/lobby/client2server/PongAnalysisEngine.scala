package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.PongProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX

import scala.util.Try

trait PongAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, pongProtocol: PongProtocol): Try[LobbyMessageProtocol]
}

object PongAnalysisEngine {
  val name: String = "lobby.client2server.Pong"
}
