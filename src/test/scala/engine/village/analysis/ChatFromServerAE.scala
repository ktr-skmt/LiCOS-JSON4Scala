package engine.village.analysis

import engine.village.Box
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.ChatFromServerAnalysisEngine
import licos.json.village.JsonChatFromServer
import play.api.libs.json.{JsValue, Json}

class ChatFromServerAE extends ChatFromServerAnalysisEngine {
  override def process(box: BOX, chatFromServer: JsonChatFromServer): Option[JsValue] = {
    box match {
      case _: Box => Option(Json.toJson(JsonTest("ChatFromServer")))
      case _ => None
    }
  }
}