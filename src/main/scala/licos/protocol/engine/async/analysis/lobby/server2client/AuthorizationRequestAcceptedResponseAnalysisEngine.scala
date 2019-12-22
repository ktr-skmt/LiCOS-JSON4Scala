package licos.protocol.engine.async.analysis.lobby.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.AuthorizationRequestAcceptedResponseProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait AuthorizationRequestAcceptedResponseAnalysisEngine extends LobbyMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(
      box:                                          LobbyBOX,
      authorizationRequestAcceptedResponseProtocol: AuthorizationRequestAcceptedResponseProtocol
  )(implicit ec:                                    ExecutionContext): Future[LobbyMessageProtocol]
}

object AuthorizationRequestAcceptedResponseAnalysisEngine {
  val name: String = "lobby.server2client.AuthorizationRequestAcceptedResponse"
}
