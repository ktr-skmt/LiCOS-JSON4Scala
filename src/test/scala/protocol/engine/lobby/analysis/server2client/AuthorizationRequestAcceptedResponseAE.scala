package protocol.engine.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.AuthorizationRequestAcceptedResponseProtocol
import licos.protocol.engine.analysis.lobby.server2client.AuthorizationRequestAcceptedResponseAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}
import protocol.engine.lobby.example.server2client.AuthorizationRequestAcceptedResponse

import scala.util.{Failure, Success, Try}

class AuthorizationRequestAcceptedResponseAE extends AuthorizationRequestAcceptedResponseAnalysisEngine {
  override def process(
      box:                                          LobbyBOX,
      authorizationRequestAcceptedResponseProtocol: AuthorizationRequestAcceptedResponseProtocol
  ): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(AuthorizationRequestAcceptedResponse.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
