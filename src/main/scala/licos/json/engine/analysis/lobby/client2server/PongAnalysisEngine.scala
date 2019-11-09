package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.JsonPong
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for a pong.
  *
  * @author Kotaro Sakamoto
  */
trait PongAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param pong a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, pong: JsonPong): Either[JsValue, JsValue]
}

object PongAnalysisEngine {

  /**
    * Pong analysis engine name.
    */
  val name:         String  = "lobby.client2server.PongAnalysisEngine"
  val isFromServer: Boolean = false

}
