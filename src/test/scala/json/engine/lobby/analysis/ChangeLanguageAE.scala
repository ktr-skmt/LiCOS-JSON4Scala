package json.engine.lobby.analysis

import json.engine.lobby.LobbyBox
import json.engine.lobby.example.ChangeLanguage
import json.element.JsonTest
import licos.json.element.lobby.JsonChangeLanguage
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.ChangeLanguageAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class ChangeLanguageAE extends ChangeLanguageAnalysisEngine {
  override def process(box: BOX, changeLang: JsonChangeLanguage): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(ChangeLanguage.`type`)))
      case _ => Left(Json.toJson(changeLang))
    }
  }
}
