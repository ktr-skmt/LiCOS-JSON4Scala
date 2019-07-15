package licos.json.engine.analysis.village

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.village.receipt.JsonReceivedPlayerMessage
import play.api.libs.json.JsValue

trait ReceivedPlayerMessageAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, receivedPlayerMessage: JsonReceivedPlayerMessage): Option[JsValue]
}
