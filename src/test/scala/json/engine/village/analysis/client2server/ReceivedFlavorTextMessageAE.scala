package json.engine.village.analysis.client2server

import json.engine.village.VillageBox
import json.engine.village.example.client2server.ReceivedFlavorTextMessage
import json.element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server.ReceivedFlavorTextMessageAnalysisEngine
import licos.json.element.village.receipt.JsonReceivedFlavorTextMessage
import play.api.libs.json.{JsValue, Json}

class ReceivedFlavorTextMessageAE extends ReceivedFlavorTextMessageAnalysisEngine {
  override def process(box: BOX, receivedFlavorTextMessage: JsonReceivedFlavorTextMessage): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(ReceivedFlavorTextMessage.`type`)))
      case _ => Left(Json.toJson(receivedFlavorTextMessage))
    }
  }
}
