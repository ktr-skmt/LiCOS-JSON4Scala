package json.engine.lobby.analysis.server2client

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.server2client.Settings
import licos.json.element.lobby.server2client.JsonSettings
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.SettingsAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class SettingsAE extends SettingsAnalysisEngine {

  override def process(box: BOX, settings: JsonSettings): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(Settings.`type`)))
      case _ => Left(Json.toJson(settings))
    }
  }

}
