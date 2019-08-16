package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.JsonIdSearch
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
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, idSearch: JsonIdSearch): Option[JsValue]
}
