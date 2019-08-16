package licos.json.engine.analysis.village.client2server

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.village.JsonStar
import play.api.libs.json.JsValue

/** The analysis engine for a star.
  *
  * @author Kotaro Sakamoto
  */
trait StarAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param star a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, star: JsonStar): Option[JsValue]
}
