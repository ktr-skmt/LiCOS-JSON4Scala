package licos.protocol.engine.analysis.auth.server2robot

import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.server2robot.AuthorizationRequestResponseProtocol
import licos.protocol.engine.processing.auth.AuthBOX

import scala.util.Try

trait AuthorizationRequestResponseAnalysisEngine {
  def process(
      box:                                  AuthBOX,
      authorizationRequestResponseProtocol: AuthorizationRequestResponseProtocol
  ): Try[AuthMessageProtocol]
}

object AuthorizationRequestResponseAnalysisEngine {
  val name: String = "auth.server2robot.AuthorizationRequestResponse"
}
