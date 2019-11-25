package licos.protocol.engine.analysis.lobby.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.LobbyProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX

import scala.util.Try

trait LobbyAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, lobbyProtocol: LobbyProtocol): Try[LobbyMessageProtocol]
}

object LobbyAnalysisEngine {
  val name: String = "lobby.server2client.Lobby"
}
