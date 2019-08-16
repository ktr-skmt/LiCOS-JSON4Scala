package licos.json.engine.analysis.village.client2server

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.element.village.JsonChatFromClient
import play.api.libs.json.JsValue

/** The analysis engine for chat from client.
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
  def process(box: BOX, chatFromClient: JsonChatFromClient): Option[JsValue]
}
