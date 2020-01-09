package protocol.engine.async.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.AuthorizationRequestAcceptedResponseProtocol
import licos.protocol.engine.async.analysis.lobby.server2client.AuthorizationRequestAcceptedResponseAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.server2client.AuthorizationRequestAcceptedResponse

import scala.concurrent.{ExecutionContext, Future}

final class AuthorizationRequestAcceptedResponseAE extends AuthorizationRequestAcceptedResponseAnalysisEngine {
  override def process(
      box:                                          LobbyBOX,
      authorizationRequestAcceptedResponseProtocol: AuthorizationRequestAcceptedResponseProtocol
  )(implicit ec:                                    ExecutionContext): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(AuthorizationRequestAcceptedResponse.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
