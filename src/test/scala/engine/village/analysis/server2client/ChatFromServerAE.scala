package engine.village.analysis.server2client

import engine.village.VillageBox
import engine.village.example.server2client.Chat
import element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.server2client.ChatAnalysisEngine
import licos.json.element.village.JsonChatFromServer
import play.api.libs.json.{JsValue, Json}

class ChatFromServerAE extends ChatAnalysisEngine {
  override def process(box: BOX, chatFromServer: JsonChatFromServer): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(Chat.`type`)))
      case _ => None
    }
  }
}