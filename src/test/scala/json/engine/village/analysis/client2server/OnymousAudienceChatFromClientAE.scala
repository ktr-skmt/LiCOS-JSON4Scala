package json.engine.village.analysis.client2server

import json.engine.village.VillageBox
import json.engine.village.example.client2server.OnymousAudienceChat
import json.element.JsonTest
import licos.json.engine.analysis.village.client2server
import licos.json.engine.BOX
import licos.json.element.village.JsonOnymousAudienceChat
import play.api.libs.json.{JsValue, Json}

class OnymousAudienceChatFromClientAE extends client2server.OnymousAudienceChatAnalysisEngine {
  override def process(box: BOX, onymousAudienceChat: JsonOnymousAudienceChat): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(OnymousAudienceChat.`type`)))
      case _ => Left(Json.toJson(onymousAudienceChat))
    }
  }
}
