package licos.json.engine.analysis.village.server2client

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.element.village.JsonFlavorText
import play.api.libs.json.JsValue

/** The analysis engine for a flavor text.
  *
  * @author Kotaro Sakamoto
  */
trait FlavorTextAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param flavorText a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, flavorText: JsonFlavorText): Option[JsValue]
}
