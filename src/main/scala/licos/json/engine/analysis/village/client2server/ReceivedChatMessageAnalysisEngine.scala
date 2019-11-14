package licos.json.engine.analysis.village.client2server

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.element.village.receipt.JsonReceivedChatMessage
import play.api.libs.json.JsValue

/** The analysis engine for a received chat message.
  *
  * @author Kotaro Sakamoto
  */
trait ReceivedChatMessageAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param receivedChatMessage a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, receivedChatMessage: JsonReceivedChatMessage): Either[JsValue, JsValue]

}

object ReceivedChatMessageAnalysisEngine {

  /**
    * Received-chat-message analysis engine name.
    */
  val name:         String  = "village.client2server.ReceivedChatMessageAnalysisEngine"
  val isFromServer: Boolean = false

}
