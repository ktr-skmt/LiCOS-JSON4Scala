package engine.village.analysis

import engine.village.Box
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.ReceivedPlayerMessageAnalysisEngine
import licos.json.village.receipt.JsonReceivedPlayerMessage
import play.api.libs.json.{JsValue, Json}

class ReceivedPlayerMessageAE extends ReceivedPlayerMessageAnalysisEngine {
  override def process(box: BOX, receivedPlayerMessage: JsonReceivedPlayerMessage): Option[JsValue] = {
    box match {
      case _: Box => Option(Json.toJson(JsonTest("ReceivedPlayerMessage")))
      case _ => None
    }
  }
}