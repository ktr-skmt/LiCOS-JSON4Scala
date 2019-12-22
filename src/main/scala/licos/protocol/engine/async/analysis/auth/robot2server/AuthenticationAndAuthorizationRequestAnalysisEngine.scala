package licos.protocol.engine.async.analysis.auth.robot2server

import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.robot2server.AuthenticationAndAuthorizationRequestProtocol
import licos.protocol.engine.processing.auth.AuthBOX

import scala.concurrent.{ExecutionContext, Future}

trait AuthenticationAndAuthorizationRequestAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(
      box:                                           AuthBOX,
      authenticationAndAuthorizationRequestProtocol: AuthenticationAndAuthorizationRequestProtocol
  )(implicit ec:                                     ExecutionContext): Future[AuthMessageProtocol]
}

object AuthenticationAndAuthorizationRequestAnalysisEngine {
  val name: String = "auth.robot2server.AuthenticationAndAuthorizationRequest"
}
