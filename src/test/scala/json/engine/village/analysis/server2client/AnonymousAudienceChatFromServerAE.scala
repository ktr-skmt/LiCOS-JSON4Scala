package json.engine.village.analysis.server2client

import json.element.JsonTest
import json.engine.village.VillageBox
import json.engine.village.example.server2client.AnonymousAudienceChat
import licos.json.element.village.JsonAnonymousAudienceChat
import licos.json.engine.BOX
import licos.json.engine.analysis.village.server2client
import play.api.libs.json.{JsValue, Json}

final class AnonymousAudienceChatFromServerAE extends server2client.AnonymousAudienceChatAnalysisEngine {
  override def process(box: BOX, audienceChatFromServer: JsonAnonymousAudienceChat): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(AnonymousAudienceChat.`type`)))
      case _ => Left(Json.toJson(audienceChatFromServer))
    }
  }
}
