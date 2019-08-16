package licos.json.engine.analysis.village.server2client

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.element.village.JsonGameResult
import play.api.libs.json.JsValue

/** The analysis engine for a game result.
  *
  * @author Kotaro Sakamoto
  */
trait GameResultAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param gameResult a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, gameResult: JsonGameResult): Option[JsValue]
}
