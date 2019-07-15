package engine.village.analysis

import engine.village.VillageBox
import engine.village.example.ReceivedSystemMessage
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.ReceivedSystemMessageAnalysisEngine
import licos.json.village.receipt.JsonReceivedSystemMessage
import play.api.libs.json.{JsValue, Json}

class ReceivedSystemMessageAE extends ReceivedSystemMessageAnalysisEngine {
  override def process(box: BOX, receivedSystemMessage: JsonReceivedSystemMessage): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(ReceivedSystemMessage.`type`)))
      case _ => None
    }
  }
}