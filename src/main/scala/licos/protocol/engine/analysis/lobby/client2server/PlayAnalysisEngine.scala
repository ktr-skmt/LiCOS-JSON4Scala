package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.PlayProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX

import scala.util.Try

trait PlayAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, playProtocol: PlayProtocol): Try[LobbyMessageProtocol]
}

object PlayAnalysisEngine {
  val name: String = "lobby.client2server.Play"
}
