package licos.json.engine.analysis.village

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.village.receipt.JsonReceivedSystemMessage
import play.api.libs.json.JsValue

trait ReceivedSystemMessageAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, receivedSystemMessage: JsonReceivedSystemMessage): Option[JsValue]
}
