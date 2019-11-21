package licos.json.engine.analysis.village

import licos.json.engine.BOX
import licos.json.engine.analysis.JsonAnalysisEngine
import licos.json.element.village.JsonError
import play.api.libs.json.JsValue

/** The analysis engine for an error.
  *
  * @author Kotaro Sakamoto
  */
trait MetaJsonErrorAnalysisEngine extends JsonAnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param error a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, error: JsonError): Either[JsValue, JsValue]
}
