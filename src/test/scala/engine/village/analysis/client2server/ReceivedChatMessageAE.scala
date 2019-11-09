package engine.village.analysis.client2server

import engine.village.VillageBox
import engine.village.example.client2server.ReceivedChatMessage
import element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server.ReceivedChatMessageAnalysisEngine
import licos.json.element.village.receipt.JsonReceivedChatMessage
import play.api.libs.json.{JsValue, Json}

class ReceivedChatMessageAE extends ReceivedChatMessageAnalysisEngine {
  override def process(box: BOX, receivedChatMessage: JsonReceivedChatMessage): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(ReceivedChatMessage.`type`)))
      case _ => Left(Json.toJson(receivedChatMessage))
    }
  }
}
