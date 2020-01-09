package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.AuthorizationRequestAcceptedProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.AuthorizationRequestAcceptedAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.AuthorizationRequestAccepted
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class AuthorizationRequestAcceptedAE extends AuthorizationRequestAcceptedAnalysisEngine {
  override def process(
      box:                                  LobbyBOX,
      authorizationRequestAcceptedProtocol: AuthorizationRequestAcceptedProtocol
  )(implicit ec:                            ExecutionContext): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(AuthorizationRequestAccepted.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
