package protocol.engine.async.auth.analysis.robot2server

import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.robot2server.AuthenticationAndAuthorizationRequestProtocol
import licos.protocol.engine.async.analysis.auth.robot2server.AuthenticationAndAuthorizationRequestAnalysisEngine
import licos.protocol.engine.processing.auth.{AuthBOX, AuthBOXNotFoundException}
import protocol.element.AuthMessageTestProtocol
import protocol.engine.auth.example.robot2server.AuthenticationAndAuthorizationRequest
import protocol.engine.auth.AuthBox

import scala.concurrent.{ExecutionContext, Future}

final class AuthenticationAndAuthorizationRequestAE extends AuthenticationAndAuthorizationRequestAnalysisEngine {
  override def process(
      box:                                           AuthBOX,
      authenticationAndAuthorizationRequestProtocol: AuthenticationAndAuthorizationRequestProtocol
  )(implicit ec:                                     ExecutionContext): Future[AuthMessageProtocol] = {
    box match {
      case _: AuthBox => Future.successful(AuthMessageTestProtocol(AuthenticationAndAuthorizationRequest.`type`))
      case _ => Future.failed(new AuthBOXNotFoundException())
    }
  }
}
