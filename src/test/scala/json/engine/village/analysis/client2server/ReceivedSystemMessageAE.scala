package json.engine.village.analysis.client2server

import json.engine.village.VillageBox
import json.engine.village.example.client2server.ReceivedSystemMessage
import json.element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server.ReceivedSystemMessageAnalysisEngine
import licos.json.element.village.receipt.JsonReceivedSystemMessage
import play.api.libs.json.{JsValue, Json}

class ReceivedSystemMessageAE extends ReceivedSystemMessageAnalysisEngine {
  override def process(box: BOX, receivedSystemMessage: JsonReceivedSystemMessage): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(ReceivedSystemMessage.`type`)))
      case _ => Left(Json.toJson(receivedSystemMessage))
    }
  }
}
