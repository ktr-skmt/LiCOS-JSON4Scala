package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.Settings
import element.JsonTest
import licos.json.element.lobby.JsonSettings
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.SettingsAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class SettingsAE extends SettingsAnalysisEngine {

  override def process(box: BOX, settings: JsonSettings): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(Settings.`type`)))
      case _ => None
    }
  }

}