package licos.protocol.engine.analysis.lobby.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.PlayedProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX

import scala.util.Try

trait PlayedAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, playedProtocol: PlayedProtocol): Try[LobbyMessageProtocol]
}

object PlayedAnalysisEngine {
  val name: String = "lobby.server2client.Played"
}
