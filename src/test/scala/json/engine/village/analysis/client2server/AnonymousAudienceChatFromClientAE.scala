package json.engine.village.analysis.client2server

import json.element.JsonTest
import json.engine.village.VillageBox
import json.engine.village.example.client2server.AnonymousAudienceChat
import licos.json.element.village.JsonAnonymousAudienceChat
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server
import play.api.libs.json.{JsValue, Json}

final class AnonymousAudienceChatFromClientAE extends client2server.AnonymousAudienceChatAnalysisEngine {
  override def process(box: BOX, anonymousAudienceChat: JsonAnonymousAudienceChat): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(AnonymousAudienceChat.`type`)))
      case _ => Left(Json.toJson(anonymousAudienceChat))
    }
  }
}
