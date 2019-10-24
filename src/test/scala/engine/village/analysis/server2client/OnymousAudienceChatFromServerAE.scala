package engine.village.analysis.server2client

import engine.village.VillageBox
import engine.village.example.server2client.OnymousAudienceChat
import element.JsonTest
import licos.json.engine.analysis.village.server2client
import licos.json.engine.BOX
import licos.json.element.village.JsonOnymousAudienceChat
import play.api.libs.json.{JsValue, Json}

class OnymousAudienceChatFromServerAE extends server2client.OnymousAudienceChatAnalysisEngine {
  override def process(box: BOX, onymousAudienceChatFromServer: JsonOnymousAudienceChat): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(OnymousAudienceChat.`type`)))
      case _ => None
    }
  }
}
