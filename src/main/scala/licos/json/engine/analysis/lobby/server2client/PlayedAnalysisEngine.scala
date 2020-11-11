package licos.json.engine.analysis.lobby.server2client

import licos.json.element.lobby.server2client.JsonPlayed
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
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, played: JsonPlayed): Either[JsValue, JsValue]

}

object PlayedAnalysisEngine {

  /** Played analysis engine name.
    */
  val name:         String  = "lobby.server2client.PlayedAnalysisEngine"
  val isFromServer: Boolean = true

}
