package licos.json.engine.analysis.village.server2client

import licos.json.element.village.server2client.JsonChatFromServer
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for a chat from server.
  *
  * @author Kotaro Sakamoto
  */
trait ChatAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param chatFromServer a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, chatFromServer: JsonChatFromServer): Either[JsValue, JsValue]

}

object ChatAnalysisEngine {

  /** Chat analysis engine name.
    */
  val name:         String  = "village.server2client.ChatAnalysisEngine"
  val isFromServer: Boolean = true

}
