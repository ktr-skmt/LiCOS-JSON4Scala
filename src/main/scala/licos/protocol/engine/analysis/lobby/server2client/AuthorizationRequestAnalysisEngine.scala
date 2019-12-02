package licos.protocol.engine.analysis.lobby.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.AuthorizationRequestProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait AuthorizationRequestAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, authorizationRequestProtocol: AuthorizationRequestProtocol): Try[LobbyMessageProtocol]
}

object AuthorizationRequestAnalysisEngine {
  val name: String = "lobby.server2client.AuthorizationRequest"
}
