package licos.json.engine.analysis.lobby.server2client

import licos.json.element.lobby.JsonPing
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for a ping.
  *
  * @author Kotaro Sakamoto
  */
trait PingAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param ping a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, ping: JsonPing): Option[JsValue]
}
