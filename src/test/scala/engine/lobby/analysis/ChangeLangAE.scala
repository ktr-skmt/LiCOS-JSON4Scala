package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.ChangeLang
import element.JsonTest
import licos.json.element.lobby.JsonChangeLang
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.ChangeLangAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class ChangeLangAE extends ChangeLangAnalysisEngine {
  override def process(box: BOX, changeLang: JsonChangeLang): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(ChangeLang.`type`)))
      case _ => None
    }
  }
}
