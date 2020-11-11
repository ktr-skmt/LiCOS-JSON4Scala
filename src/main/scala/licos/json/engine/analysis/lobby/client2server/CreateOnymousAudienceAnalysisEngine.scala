package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonCreateOnymousAudience
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for creating an onymous audience.
  *
  * @author Kotaro Sakamoto
  */
trait CreateOnymousAudienceAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param createOnymousAudience a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, createOnymousAudience: JsonCreateOnymousAudience): Either[JsValue, JsValue]
}

object CreateOnymousAudienceAnalysisEngine {

  /** Create-onymous-audience analysis engine name.
    */
  val name:         String  = "lobby.client2server.CreateOnymousAudienceAnalysisEngine"
  val isFromServer: Boolean = false
}
