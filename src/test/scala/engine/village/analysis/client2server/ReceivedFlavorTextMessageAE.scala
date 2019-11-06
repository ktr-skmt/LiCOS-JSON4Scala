package engine.village.analysis.client2server

import engine.village.VillageBox
import engine.village.example.client2server.ReceivedFlavorTextMessage
import element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server.ReceivedFlavorTextMessageAnalysisEngine
import licos.json.element.village.receipt.JsonReceivedFlavorTextMessage
import play.api.libs.json.{JsValue, Json}

class ReceivedFlavorTextMessageAE extends ReceivedFlavorTextMessageAnalysisEngine {
  override def process(box: BOX, receivedFlavorTextMessage: JsonReceivedFlavorTextMessage): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(ReceivedFlavorTextMessage.`type`)))
      case _ => None
    }
  }
}
