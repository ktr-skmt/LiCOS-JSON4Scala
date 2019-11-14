package licos.json.engine.analysis.lobby.server2server

import licos.json.element.lobby.JsonPlayedWithToken
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for played-with-token.
  *
  * @author Kotaro Sakamoto
  */
trait PlayedWithTokenAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param playedWithToken a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, playedWithToken: JsonPlayedWithToken): Either[JsValue, JsValue]

}

object PlayedWithTokenAnalysisEngine {

  /**
    * Played-with-token analysis engine name.
    */
  val name:         String  = "lobby.server2server.PlayedWithTokenAnalysisEngine"
  val isFromServer: Boolean = true

}
