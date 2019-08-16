package engine.village.analysis.client2server

import engine.village.VillageBox
import engine.village.example.client2server.AudienceChat
import element.JsonTest
import licos.json.engine.BOX
import licos.json.village.JsonAudienceChat
import play.api.libs.json.{JsValue, Json}

class AudienceChatFromClientAE extends licos.json.engine.analysis.village.client2server.AudienceChatAnalysisEngine {
  override def process(box: BOX, audienceChat: JsonAudienceChat): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(AudienceChat.`type`)))
      case _ => None
    }
  }
}
