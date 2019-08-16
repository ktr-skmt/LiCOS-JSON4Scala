package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.JsonPlay
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for playing a game.
  *
  * @author Kotaro Sakamoto
  */
trait PlayAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param play a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, play: JsonPlay): Option[JsValue]
}
