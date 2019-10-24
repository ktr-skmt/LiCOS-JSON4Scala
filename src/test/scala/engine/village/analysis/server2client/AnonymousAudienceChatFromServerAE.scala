package engine.village.analysis.server2client

import element.JsonTest
import engine.village.VillageBox
import engine.village.example.server2client.AnonymousAudienceChat
import licos.json.element.village.JsonAnonymousAudienceChat
import licos.json.engine.BOX
import licos.json.engine.analysis.village.server2client
import play.api.libs.json.{JsValue, Json}

class AnonymousAudienceChatFromServerAE extends server2client.AnonymousAudienceChatAnalysisEngine {
  override def process(box: BOX, audienceChatFromServer: JsonAnonymousAudienceChat): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(AnonymousAudienceChat.`type`)))
      case _ => None
    }
  }
}
