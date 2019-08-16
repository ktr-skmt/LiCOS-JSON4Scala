package licos.json.engine.analysis.village.client2server

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.element.village.receipt.JsonReceivedFlavorTextMessage
import play.api.libs.json.JsValue

/** The analysis engine for a received flavor text message.
  *
  * @author Kotaro Sakamoto
  */
trait ReceivedFlavorTextMessageAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param receivedFlavorTextMessage a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, receivedFlavorTextMessage: JsonReceivedFlavorTextMessage): Option[JsValue]
}
