package licos.json.engine.analysis.village.client2server

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.element.village.receipt.JsonReceivedSystemMessage
import play.api.libs.json.JsValue

/** The analysis engine for a received system message.
  *
  * @author Kotaro Sakamoto
  */
trait ReceivedSystemMessageAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param receivedSystemMessage a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, receivedSystemMessage: JsonReceivedSystemMessage): Option[JsValue]
}
