package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonAuthorizationRequestAccepted
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

trait AuthorizationRequestAcceptedAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, authorizationRequestAccepted: JsonAuthorizationRequestAccepted): Either[JsValue, JsValue]
}

object AuthorizationRequestAcceptedAnalysisEngine {
  val name:         String  = "lobby.client2server.AuthorizationRequestAcceptedAnalysisEngine"
  val isFromServer: Boolean = false
}
