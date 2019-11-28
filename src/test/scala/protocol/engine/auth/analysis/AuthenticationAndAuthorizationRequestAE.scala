package protocol.engine.auth.analysis

import licos.json.element.auth.robot2server.JsonAuthenticationAndAuthorizationRequest
import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.robot2server.AuthenticationAndAuthorizationRequestProtocol
import licos.protocol.engine.analysis.auth.robot2server.AuthenticationAndAuthorizationRequestAnalysisEngine
import licos.protocol.engine.processing.AuthBOX
import protocol.element.AuthMessageTestProtocol
import protocol.engine.auth.{AuthBox, NoAuthBOXException}

import scala.util.{Failure, Success, Try}

class AuthenticationAndAuthorizationRequestAE extends AuthenticationAndAuthorizationRequestAnalysisEngine {
  override def process(
      box:                                           AuthBOX,
      authenticationAndAuthorizationRequestProtocol: AuthenticationAndAuthorizationRequestProtocol
  ): Try[AuthMessageProtocol] = {
    box match {
      case _: AuthBox => Success(AuthMessageTestProtocol(JsonAuthenticationAndAuthorizationRequest.`type`))
      case _ => Failure(new NoAuthBOXException())
    }
  }
}
