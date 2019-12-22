package protocol.engine.async.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.AuthorizationRequestProtocol
import licos.protocol.engine.async.analysis.lobby.server2client.AuthorizationRequestAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.server2client.AuthorizationRequest

import scala.concurrent.{ExecutionContext, Future}

final class AuthorizationRequestAE extends AuthorizationRequestAnalysisEngine {
  override def process(
      box:                          LobbyBOX,
      authorizationRequestProtocol: AuthorizationRequestProtocol
  )(implicit ec:                    ExecutionContext): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future(LobbyMessageTestProtocol(AuthorizationRequest.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
