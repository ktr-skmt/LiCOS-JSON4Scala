package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonPlay
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
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, play: JsonPlay): Either[JsValue, JsValue]
}

object PlayAnalysisEngine {

  /** Play analysis engine name.
    */
  val name:         String  = "lobby.client2server.PlayAnalysisEngine"
  val isFromServer: Boolean = false

}
