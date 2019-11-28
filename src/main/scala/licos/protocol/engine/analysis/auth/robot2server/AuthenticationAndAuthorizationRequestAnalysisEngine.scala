package licos.protocol.engine.analysis.auth.robot2server

import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.robot2server.AuthenticationAndAuthorizationRequestProtocol
import licos.protocol.engine.processing.AuthBOX

import scala.util.Try

trait AuthenticationAndAuthorizationRequestAnalysisEngine {
  def process(
      box:                                           AuthBOX,
      authenticationAndAuthorizationRequestProtocol: AuthenticationAndAuthorizationRequestProtocol
  ): Try[AuthMessageProtocol]
}

object AuthenticationAndAuthorizationRequestAnalysisEngine {
  val name: String = "auth.robot2server.AuthenticationAndAuthorizationRequest"
}
