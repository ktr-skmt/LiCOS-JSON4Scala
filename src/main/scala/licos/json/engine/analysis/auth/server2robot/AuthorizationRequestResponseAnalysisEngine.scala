package licos.json.engine.analysis.auth.server2robot

import licos.json.element.auth.server2robot.JsonAuthorizationRequestResponse
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

trait AuthorizationRequestResponseAnalysisEngine extends AnalysisEngine {
  def process(
      box:                          BOX,
      authorizationRequestResponse: JsonAuthorizationRequestResponse
  ): Either[JsValue, JsValue]
}

object AuthorizationRequestResponseAnalysisEngine {
  val name:         String  = "auth.robot2server.AuthorizationRequestResponseAnalysisEngine"
  val isFromServer: Boolean = true
}
