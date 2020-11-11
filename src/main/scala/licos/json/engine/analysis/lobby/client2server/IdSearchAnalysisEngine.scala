package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonIdSearch
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for an id search.
  *
  * @author Kotaro Sakamoto
  */
trait IdSearchAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param idSearch a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, idSearch: JsonIdSearch): Either[JsValue, JsValue]

}

object IdSearchAnalysisEngine {

  /** Id-search analysis engine name.
    */
  val name:         String  = "lobby.client2server.IdSearchAnalysisEngine"
  val isFromServer: Boolean = false

}
