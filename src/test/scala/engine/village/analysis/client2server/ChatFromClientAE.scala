package engine.village.analysis.client2server

import engine.village.VillageBox
import engine.village.example.client2server.Chat
import element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server.ChatAnalysisEngine
import licos.json.village.JsonChatFromClient
import play.api.libs.json.{JsValue, Json}

class ChatFromClientAE extends ChatAnalysisEngine {
  override def process(box: BOX, chatFromClient: JsonChatFromClient): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(Chat.`type`)))
      case _ => None
    }
  }
}