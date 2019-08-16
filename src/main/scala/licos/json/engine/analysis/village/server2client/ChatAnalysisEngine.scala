package licos.json.engine.analysis.village.server2client

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.element.village.JsonChatFromServer
import play.api.libs.json.JsValue

/** The analysis engine for a chat from server.
  *
  * @author Kotaro Sakamoto
  */
trait ChatAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param chatFromClient a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, chatFromClient: JsonChatFromServer): Option[JsValue]
}
