package protocol.engine.auth.analysis.robot2server

import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.robot2server.AuthenticationAndAuthorizationRequestProtocol
import licos.protocol.engine.analysis.auth.robot2server.AuthenticationAndAuthorizationRequestAnalysisEngine
import licos.protocol.engine.processing.auth.{AuthBOX, AuthBOXNotFoundException}
import protocol.element.AuthMessageTestProtocol
import protocol.engine.auth.example.robot2server.AuthenticationAndAuthorizationRequest
import protocol.engine.auth.AuthBox

import scala.util.{Failure, Success, Try}

final class AuthenticationAndAuthorizationRequestAE extends AuthenticationAndAuthorizationRequestAnalysisEngine {
  override def process(
      box:                                           AuthBOX,
      authenticationAndAuthorizationRequestProtocol: AuthenticationAndAuthorizationRequestProtocol
  ): Try[AuthMessageProtocol] = {
    box match {
      case _: AuthBox => Success(AuthMessageTestProtocol(AuthenticationAndAuthorizationRequest.`type`))
      case _ => Failure(new AuthBOXNotFoundException())
    }
  }
}
