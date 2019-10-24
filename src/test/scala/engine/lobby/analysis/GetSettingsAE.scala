package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.GetSettings
import element.JsonTest
import licos.json.element.lobby.JsonGetSettings
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.GetSettingsAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class GetSettingsAE extends GetSettingsAnalysisEngine {
  override def process(box: BOX, getSettings: JsonGetSettings): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(GetSettings.`type`)))
      case _ => None
    }
  }
}