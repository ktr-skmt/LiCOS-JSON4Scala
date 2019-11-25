package licos.json.engine.analysis.lobby.server2client

import licos.json.element.lobby.server2client.JsonAuthorizationRequestAcceptedResponse
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

trait AuthorizationRequestAcceptedResponseAnalysisEngine extends AnalysisEngine {
  def process(
      box:                                  BOX,
      authorizationRequestAcceptedResponse: JsonAuthorizationRequestAcceptedResponse
  ): Either[JsValue, JsValue]
}

object AuthorizationRequestAcceptedResponseAnalysisEngine {
  val name:         String  = "lobby.server2client.AuthorizationRequestAcceptedResponseAnalysisEngine"
  val isFromServer: Boolean = true
}
