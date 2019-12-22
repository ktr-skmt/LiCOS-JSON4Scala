package licos.protocol.engine.async.analysis.auth.server2robot

import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.server2robot.AuthenticationRequestResponseProtocol
import licos.protocol.engine.processing.auth.AuthBOX

import scala.concurrent.{ExecutionContext, Future}

trait AuthenticationRequestResponseAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(
      box:                                   AuthBOX,
      authenticationRequestResponseProtocol: AuthenticationRequestResponseProtocol
  )(implicit ec:                             ExecutionContext): Future[AuthMessageProtocol]
}

object AuthenticationRequestResponseAnalysisEngine {
  val name: String = "auth.server2robot.AuthenticationRequestResponse"
}
