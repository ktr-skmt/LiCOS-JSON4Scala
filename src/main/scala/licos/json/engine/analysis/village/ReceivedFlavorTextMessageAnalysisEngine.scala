package licos.json.engine.analysis.village

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.village.receipt.JsonReceivedFlavorTextMessage
import play.api.libs.json.JsValue

trait ReceivedFlavorTextMessageAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, receivedFlavorTextMessage: JsonReceivedFlavorTextMessage): Option[JsValue]
}
