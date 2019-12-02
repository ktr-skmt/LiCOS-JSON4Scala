package json.engine.village.analysis.server2client

import json.engine.village.VillageBox
import json.engine.village.example.server2client.OnymousAudienceChat
import json.element.JsonTest
import licos.json.engine.analysis.village.server2client
import licos.json.engine.BOX
import licos.json.element.village.JsonOnymousAudienceChat
import play.api.libs.json.{JsValue, Json}

class OnymousAudienceChatFromServerAE extends server2client.OnymousAudienceChatAnalysisEngine {
  override def process(box: BOX, onymousAudienceChatFromServer: JsonOnymousAudienceChat): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(OnymousAudienceChat.`type`)))
      case _ => Left(Json.toJson(onymousAudienceChatFromServer))
    }
  }
}
