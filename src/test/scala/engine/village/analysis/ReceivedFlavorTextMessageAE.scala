package engine.village.analysis

import engine.village.VillageBox
import engine.village.example.ReceivedFlavorTextMessage
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.ReceivedFlavorTextMessageAnalysisEngine
import licos.json.village.receipt.JsonReceivedFlavorTextMessage
import play.api.libs.json.{JsValue, Json}

class ReceivedFlavorTextMessageAE extends ReceivedFlavorTextMessageAnalysisEngine {
  override def process(box: BOX, receivedFlavorTextMessage: JsonReceivedFlavorTextMessage): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(ReceivedFlavorTextMessage.`type`)))
      case _ => None
    }
  }
}