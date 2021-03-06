package protocol.engine.lobby.analysis.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.AuthorizationRequestProtocol
import licos.protocol.engine.analysis.lobby.server2client.AuthorizationRequestAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.server2client.AuthorizationRequest

import scala.util.{Failure, Success, Try}

final class AuthorizationRequestAE extends AuthorizationRequestAnalysisEngine {
  override def process(
      box:                          LobbyBOX,
      authorizationRequestProtocol: AuthorizationRequestProtocol
  ): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(AuthorizationRequest.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
