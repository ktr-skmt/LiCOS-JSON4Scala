package licos.json.engine.analysis.auth.robot2server

import licos.json.element.auth.robot2server.JsonAuthenticationAndAuthorizationRequest
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

trait AuthenticationAndAuthorizationRequestAnalysisEngine extends AnalysisEngine {
  def process(
      box:                                   BOX,
      authenticationAndAuthorizationRequest: JsonAuthenticationAndAuthorizationRequest
  ): Either[JsValue, JsValue]
}

object AuthenticationAndAuthorizationRequestAnalysisEngine {
  val name:         String  = "auth.server2robot.AuthenticationAndAuthorizationRequestAnalysisEngine"
  val isFromServer: Boolean = false
}
