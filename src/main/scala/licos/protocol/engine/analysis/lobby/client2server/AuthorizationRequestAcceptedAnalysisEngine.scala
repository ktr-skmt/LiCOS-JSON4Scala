package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.AuthorizationRequestAcceptedProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX

import scala.util.Try

trait AuthorizationRequestAcceptedAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(
      box:                                  LobbyBOX,
      authorizationRequestAcceptedProtocol: AuthorizationRequestAcceptedProtocol
  ): Try[LobbyMessageProtocol]
}

object AuthorizationRequestAcceptedAnalysisEngine {
  val name: String = "lobby.client2server.AuthorizationRequestAccepted"
}
