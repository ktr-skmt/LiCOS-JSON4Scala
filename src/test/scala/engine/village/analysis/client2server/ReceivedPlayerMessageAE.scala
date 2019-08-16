package engine.village.analysis.client2server

import engine.village.VillageBox
import engine.village.example.client2server.ReceivedPlayerMessage
import element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server.ReceivedPlayerMessageAnalysisEngine
import licos.json.element.village.receipt.JsonReceivedPlayerMessage
import play.api.libs.json.{JsValue, Json}

class ReceivedPlayerMessageAE extends ReceivedPlayerMessageAnalysisEngine {
  override def process(box: BOX, receivedPlayerMessage: JsonReceivedPlayerMessage): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(ReceivedPlayerMessage.`type`)))
      case _ => None
    }
  }
}