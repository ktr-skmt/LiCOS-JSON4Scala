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
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, receivedSystemMessage: JsonReceivedSystemMessage): Either[JsValue, JsValue]

}

object ReceivedSystemMessageAnalysisEngine {

  /**
    * Received-system-message analysis engine name.
    */
  val name:         String  = "village.client2server.ReceivedSystemMessageAnalysisEngine"
  val isFromServer: Boolean = false

}
