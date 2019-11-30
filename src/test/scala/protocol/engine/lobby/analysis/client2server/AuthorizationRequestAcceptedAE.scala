package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.AuthorizationRequestAcceptedProtocol
import licos.protocol.engine.analysis.lobby.client2server.AuthorizationRequestAcceptedAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.AuthorizationRequestAccepted
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class AuthorizationRequestAcceptedAE extends AuthorizationRequestAcceptedAnalysisEngine {
  override def process(
      box:                                  LobbyBOX,
      authorizationRequestAcceptedProtocol: AuthorizationRequestAcceptedProtocol
  ): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(AuthorizationRequestAccepted.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
