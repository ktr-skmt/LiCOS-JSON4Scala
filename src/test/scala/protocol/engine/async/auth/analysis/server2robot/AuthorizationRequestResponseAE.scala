package protocol.engine.async.auth.analysis.server2robot

import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.server2robot.AuthorizationRequestResponseProtocol
import licos.protocol.engine.async.analysis.auth.server2robot.AuthorizationRequestResponseAnalysisEngine
import licos.protocol.engine.processing.auth.{AuthBOX, AuthBOXNotFoundException}
import protocol.element.AuthMessageTestProtocol
import protocol.engine.auth.example.server2robot.AuthorizationRequestResponse
import protocol.engine.auth.AuthBox

import scala.concurrent.{ExecutionContext, Future}

final class AuthorizationRequestResponseAE extends AuthorizationRequestResponseAnalysisEngine {
  override def process(
      box:                                  AuthBOX,
      authorizationRequestResponseProtocol: AuthorizationRequestResponseProtocol
  )(implicit ec:                            ExecutionContext): Future[AuthMessageProtocol] = {
    box match {
      case _: AuthBox => Future.successful(AuthMessageTestProtocol(AuthorizationRequestResponse.`type`))
      case _ => Future.failed(new AuthBOXNotFoundException())
    }
  }
}
