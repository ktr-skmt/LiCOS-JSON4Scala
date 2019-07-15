package engine.village.analysis

import engine.village.VillageBox
import engine.village.example.ChatFromClient
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.ChatFromClientAnalysisEngine
import licos.json.village.JsonChatFromClient
import play.api.libs.json.{JsValue, Json}

class ChatFromClientAE extends ChatFromClientAnalysisEngine {
  override def process(box: BOX, chatFromClient: JsonChatFromClient): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(ChatFromClient.`type`)))
      case _ => None
    }
  }
}