package licos.protocol.engine.analysis.lobby.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.PingProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX

import scala.util.Try

trait PingAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, pingProtocol: PingProtocol): Try[LobbyMessageProtocol]
}

object PingAnalysisEngine {
  val name: String = "lobby.server2client.Ping"
}
