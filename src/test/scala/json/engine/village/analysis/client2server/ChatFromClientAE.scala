package json.engine.village.analysis.client2server

import json.engine.village.VillageBox
import json.engine.village.example.client2server.Chat
import json.element.JsonTest
import licos.json.element.village.client2server.JsonChatFromClient
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server.ChatAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class ChatFromClientAE extends ChatAnalysisEngine {
  override def process(box: BOX, chatFromClient: JsonChatFromClient): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(Chat.`type`)))
      case _ => Left(Json.toJson(chatFromClient))
    }
  }
}
