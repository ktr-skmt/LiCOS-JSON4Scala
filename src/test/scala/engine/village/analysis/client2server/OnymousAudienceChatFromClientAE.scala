package engine.village.analysis.client2server

import engine.village.VillageBox
import engine.village.example.client2server.OnymousAudienceChat
import element.JsonTest
import licos.json.engine.analysis.village.client2server
import licos.json.engine.BOX
import licos.json.element.village.JsonOnymousAudienceChat
import play.api.libs.json.{JsValue, Json}

class OnymousAudienceChatFromClientAE extends client2server.OnymousAudienceChatAnalysisEngine {
  override def process(box: BOX, onymousAudienceChat: JsonOnymousAudienceChat): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(OnymousAudienceChat.`type`)))
      case _ => None
    }
  }
}
