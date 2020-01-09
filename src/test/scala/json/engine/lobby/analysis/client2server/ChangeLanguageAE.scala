package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.ChangeLanguage
import licos.json.element.lobby.client2server.JsonChangeLanguage
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.ChangeLanguageAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class ChangeLanguageAE extends ChangeLanguageAnalysisEngine {
  override def process(box: BOX, changeLanguage: JsonChangeLanguage): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(ChangeLanguage.`type`)))
      case _ => Left(Json.toJson(changeLanguage))
    }
  }
}
