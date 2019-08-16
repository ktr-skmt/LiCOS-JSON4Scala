package engine.village.analysis.server2client

import engine.village.VillageBox
import engine.village.example.server2client.AudienceChat
import element.JsonTest
import licos.json.engine.BOX
import licos.json.village.JsonAudienceChat
import play.api.libs.json.{JsValue, Json}

class AudienceChatFromServerAE extends licos.json.engine.analysis.village.server2client.AudienceChatAnalysisEngine {
  override def process(box: BOX, audienceChatFromServer: JsonAudienceChat): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(AudienceChat.`type`)))
      case _ => None
    }
  }
}
