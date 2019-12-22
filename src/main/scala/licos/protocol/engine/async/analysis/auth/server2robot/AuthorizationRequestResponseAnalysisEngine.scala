package licos.protocol.engine.async.analysis.auth.server2robot

import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.server2robot.AuthorizationRequestResponseProtocol
import licos.protocol.engine.processing.auth.AuthBOX

import scala.concurrent.{ExecutionContext, Future}

trait AuthorizationRequestResponseAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(
      box:                                  AuthBOX,
      authorizationRequestResponseProtocol: AuthorizationRequestResponseProtocol
  )(implicit ec:                            ExecutionContext): Future[AuthMessageProtocol]
}

object AuthorizationRequestResponseAnalysisEngine {
  val name: String = "auth.server2robot.AuthorizationRequestResponse"
}
