package licos.json.engine.analysis.village.client2server

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.village.receipt.JsonReceivedPlayerMessage
import play.api.libs.json.JsValue

/** The analysis engine for a received player message.
  *
  * @author Kotaro Sakamoto
  */
trait ReceivedPlayerMessageAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param receivedPlayerMessage a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, receivedPlayerMessage: JsonReceivedPlayerMessage): Option[JsValue]
}
