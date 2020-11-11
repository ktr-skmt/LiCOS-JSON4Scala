package licos.json.engine.analysis.lobby.server2client

import licos.json.element.lobby.server2client.JsonPing
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
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, ping: JsonPing): Either[JsValue, JsValue]

}

object PingAnalysisEngine {

  /** Ping analysis engine name.
    */
  val name:         String  = "lobby.server2client.PingAnalysisEngine"
  val isFromServer: Boolean = true

}
