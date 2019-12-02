package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.EnterLobbyProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait EnterLobbyAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, enterLobbyProtocol: EnterLobbyProtocol): Try[LobbyMessageProtocol]
}

object EnterLobbyAnalysisEngine {
  val name: String = "lobby.client2server.EnterLobby"
}
