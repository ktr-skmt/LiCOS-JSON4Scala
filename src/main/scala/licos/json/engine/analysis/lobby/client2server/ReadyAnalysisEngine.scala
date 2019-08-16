package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.JsonReady
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for readying up.
  *
  * @author Kotaro Sakamoto
  */
trait ReadyAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param ready a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, ready: JsonReady): Option[JsValue]
}
