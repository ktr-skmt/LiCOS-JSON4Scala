package licos.json.engine.analysis.lobby.server2client

import licos.json.element.lobby.JsonPlayed
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for played.
  *
  * @author Kotaro Sakamoto
  */
trait PlayedAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param played a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, played: JsonPlayed): Option[JsValue]
}