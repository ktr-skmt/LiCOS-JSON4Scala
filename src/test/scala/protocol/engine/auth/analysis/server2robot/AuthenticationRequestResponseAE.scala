package protocol.engine.auth.analysis.server2robot

import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.server2robot.AuthenticationRequestResponseProtocol
import licos.protocol.engine.analysis.auth.server2robot.AuthenticationRequestResponseAnalysisEngine
import licos.protocol.engine.processing.auth.AuthBOX
import protocol.element.AuthMessageTestProtocol
import protocol.engine.auth.example.server2robot.AuthenticationRequestResponse
import protocol.engine.auth.{AuthBox, NoAuthBOXException}

import scala.util.{Failure, Success, Try}

class AuthenticationRequestResponseAE extends AuthenticationRequestResponseAnalysisEngine {
  override def process(
      box:                                   AuthBOX,
      authenticationRequestResponseProtocol: AuthenticationRequestResponseProtocol
  ): Try[AuthMessageProtocol] = {
    box match {
      case _: AuthBox => Success(AuthMessageTestProtocol(AuthenticationRequestResponse.`type`))
      case _ => Failure(new NoAuthBOXException())
    }
  }
}
