package engine.village.analysis

import engine.village.Box
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.ChatFromClientAnalysisEngine
import licos.json.village.JsonChatFromClient
import play.api.libs.json.{JsValue, Json}

class ChatFromClientAE extends ChatFromClientAnalysisEngine {
  override def process(box: BOX, chatFromClient: JsonChatFromClient): Option[JsValue] = {
    box match {
      case _: Box => Option(Json.toJson(JsonTest("ChatFromClient")))
      case _ => None
    }
  }
}