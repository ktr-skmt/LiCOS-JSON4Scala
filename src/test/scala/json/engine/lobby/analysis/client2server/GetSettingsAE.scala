package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.GetSettings
import licos.json.element.lobby.client2server.JsonGetSettings
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.GetSettingsAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class GetSettingsAE extends GetSettingsAnalysisEngine {
  override def process(box: BOX, getSettings: JsonGetSettings): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(GetSettings.`type`)))
      case _ => Left(Json.toJson(getSettings))
    }
  }
}
