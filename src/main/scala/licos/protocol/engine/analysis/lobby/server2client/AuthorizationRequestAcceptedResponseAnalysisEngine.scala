package licos.protocol.engine.analysis.lobby.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.AuthorizationRequestAcceptedResponseProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX

import scala.util.Try

trait AuthorizationRequestAcceptedResponseAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(
      box:                                          LobbyBOX,
      authorizationRequestAcceptedResponseProtocol: AuthorizationRequestAcceptedResponseProtocol
  ): Try[LobbyMessageProtocol]
}

object AuthorizationRequestAcceptedResponseAnalysisEngine {
  val name: String = "lobby.server2client.AuthorizationRequestAcceptedResponse"
}
