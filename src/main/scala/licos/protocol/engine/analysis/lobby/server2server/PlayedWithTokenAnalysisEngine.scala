package licos.protocol.engine.analysis.lobby.server2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2server.PlayedWithTokenProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX

import scala.util.Try

trait PlayedWithTokenAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, playedWithTokenProtocol: PlayedWithTokenProtocol): Try[LobbyMessageProtocol]
}

object PlayedWithTokenAnalysisEngine {
  val name: String = "lobby.server2server.PlayedWithToken"
}
