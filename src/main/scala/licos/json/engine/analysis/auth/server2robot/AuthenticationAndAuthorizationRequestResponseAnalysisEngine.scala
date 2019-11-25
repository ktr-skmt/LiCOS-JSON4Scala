package licos.json.engine.analysis.auth.server2robot

import licos.json.element.auth.server2robot.JsonAuthenticationAndAuthorizationRequestResponse
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

trait AuthenticationAndAuthorizationRequestResponseAnalysisEngine extends AnalysisEngine {
  def process(
      box:                                           BOX,
      authenticationAndAuthorizationRequestResponse: JsonAuthenticationAndAuthorizationRequestResponse
  ): Either[JsValue, JsValue]
}

object AuthenticationAndAuthorizationRequestResponseAnalysisEngine {
  val name:         String  = "auth.robot2server.AuthenticationAndAuthorizationRequestResponseAnalysisEngine"
  val isFromServer: Boolean = true
}
