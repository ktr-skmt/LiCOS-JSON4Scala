package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonSelectOnymousAudience
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for selecting an onymous audience.
  *
  * @author Kotaro Sakamoto
  */
trait SelectOnymousAudienceAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param selectOnymousAudience a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, selectOnymousAudience: JsonSelectOnymousAudience): Either[JsValue, JsValue]
}

object SelectOnymousAudienceAnalysisEngine {

  /**
    * Select-onymous-audience analysis engine name.
    */
  val name:         String  = "lobby.client2server.SelectOnymousAudienceAnalysisEngine"
  val isFromServer: Boolean = false
}
