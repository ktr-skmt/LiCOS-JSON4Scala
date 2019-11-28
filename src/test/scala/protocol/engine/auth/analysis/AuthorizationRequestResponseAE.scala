package protocol.engine.auth.analysis

import licos.json.element.auth.server2robot.JsonAuthorizationRequestResponse
import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.server2robot.AuthorizationRequestResponseProtocol
import licos.protocol.engine.analysis.auth.server2robot.AuthorizationRequestResponseAnalysisEngine
import licos.protocol.engine.processing.AuthBOX
import protocol.element.AuthMessageTestProtocol
import protocol.engine.auth.{AuthBox, NoAuthBOXException}

import scala.util.{Failure, Success, Try}

class AuthorizationRequestResponseAE extends AuthorizationRequestResponseAnalysisEngine {
  override def process(
      box:                                  AuthBOX,
      authorizationRequestResponseProtocol: AuthorizationRequestResponseProtocol
  ): Try[AuthMessageProtocol] = {
    box match {
      case _: AuthBox => Success(AuthMessageTestProtocol(JsonAuthorizationRequestResponse.`type`))
      case _ => Failure(new NoAuthBOXException())
    }
  }
}
