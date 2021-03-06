package json.engine.village.analysis.server2client

import json.engine.village.VillageBox
import json.engine.village.example.server2client.Chat
import json.element.JsonTest
import licos.json.element.village.server2client.JsonChatFromServer
import licos.json.engine.BOX
import licos.json.engine.analysis.village.server2client.ChatAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class ChatFromServerAE extends ChatAnalysisEngine {
  override def process(box: BOX, chatFromServer: JsonChatFromServer): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(Chat.`type`)))
      case _ => Left(Json.toJson(chatFromServer))
    }
  }
}
