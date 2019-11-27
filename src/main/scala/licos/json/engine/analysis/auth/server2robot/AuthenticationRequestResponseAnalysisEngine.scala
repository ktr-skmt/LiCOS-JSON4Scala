package licos.json.engine.analysis.auth.server2robot

import licos.json.element.auth.server2robot.JsonAuthenticationRequestResponse
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

trait AuthenticationRequestResponseAnalysisEngine extends AnalysisEngine {
  def process(
      box:                           BOX,
      authenticationRequestResponse: JsonAuthenticationRequestResponse
  ): Either[JsValue, JsValue]
}

object AuthenticationRequestResponseAnalysisEngine {
  val name:         String  = "auth.robot2server.AuthenticationRequestResponseAnalysisEngine"
  val isFromServer: Boolean = true
}
