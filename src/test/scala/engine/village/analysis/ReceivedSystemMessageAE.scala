package engine.village.analysis

import engine.village.Box
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.ReceivedSystemMessageAnalysisEngine
import licos.json.village.receipt.JsonReceivedSystemMessage
import play.api.libs.json.{JsValue, Json}

class ReceivedSystemMessageAE extends ReceivedSystemMessageAnalysisEngine {
  override def process(box: BOX, receivedSystemMessage: JsonReceivedSystemMessage): Option[JsValue] = {
    box match {
      case _: Box => Option(Json.toJson(JsonTest("ReceivedSystemMessage")))
      case _ => None
    }
  }
}