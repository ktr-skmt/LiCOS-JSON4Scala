package licos.protocol.engine.analysis.auth.server2robot

import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.server2robot.AuthenticationRequestResponseProtocol
import licos.protocol.engine.processing.AuthBOX

import scala.util.Try

trait AuthenticationRequestResponseAnalysisEngine {
  def process(
      box:                                   AuthBOX,
      authenticationRequestResponseProtocol: AuthenticationRequestResponseProtocol
  ): Try[AuthMessageProtocol]
}

object AuthenticationRequestResponseAnalysisEngine {
  val name: String = "auth.server2robot.AuthenticationRequestResponse"
}
