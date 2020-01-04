package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.CreateOnymousAudience
import licos.json.element.lobby.client2server.JsonCreateOnymousAudience
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.CreateOnymousAudienceAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class CreateOnymousAudienceAE extends CreateOnymousAudienceAnalysisEngine {
  override def process(box: BOX, createOnymousAudience: JsonCreateOnymousAudience): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(CreateOnymousAudience.`type`)))
      case _ => Left(Json.toJson(createOnymousAudience))
    }
  }
}
