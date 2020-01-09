package protocol.engine.async.auth.analysis.server2robot

import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.server2robot.AuthenticationRequestResponseProtocol
import licos.protocol.engine.async.analysis.auth.server2robot.AuthenticationRequestResponseAnalysisEngine
import licos.protocol.engine.processing.auth.{AuthBOX, AuthBOXNotFoundException}
import protocol.element.AuthMessageTestProtocol
import protocol.engine.auth.example.server2robot.AuthenticationRequestResponse
import protocol.engine.auth.AuthBox

import scala.concurrent.{ExecutionContext, Future}

final class AuthenticationRequestResponseAE extends AuthenticationRequestResponseAnalysisEngine {
  override def process(
      box:                                   AuthBOX,
      authenticationRequestResponseProtocol: AuthenticationRequestResponseProtocol
  )(implicit ec:                             ExecutionContext): Future[AuthMessageProtocol] = {
    box match {
      case _: AuthBox => Future.successful(AuthMessageTestProtocol(AuthenticationRequestResponse.`type`))
      case _ => Future.failed(new AuthBOXNotFoundException())
    }
  }
}
