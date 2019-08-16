package licos.json.engine.analysis.village

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.village.JsonError
import play.api.libs.json.JsValue

/** The analysis engine for an error.
  *
  * @author Kotaro Sakamoto
  */
trait ErrorAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param error a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, error: JsonError): Option[JsValue]
}
