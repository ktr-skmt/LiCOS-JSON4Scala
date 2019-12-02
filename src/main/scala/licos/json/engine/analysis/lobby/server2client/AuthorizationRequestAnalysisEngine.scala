package licos.json.engine.analysis.lobby.server2client

import licos.json.element.lobby.server2client.JsonAuthorizationRequest
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

trait AuthorizationRequestAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, authorizationRequest: JsonAuthorizationRequest): Either[JsValue, JsValue]
}

object AuthorizationRequestAnalysisEngine {
  val name:         String  = "lobby.server2client.AuthorizationRequestAnalysisEngine"
  val isFromServer: Boolean = true
}
