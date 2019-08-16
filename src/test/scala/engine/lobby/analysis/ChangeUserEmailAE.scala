package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.ChangeUserEmail
import element.JsonTest
import licos.json.element.lobby.JsonChangeUserEmail
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.ChangeUserEmailAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class ChangeUserEmailAE extends ChangeUserEmailAnalysisEngine {
  override def process(box: BOX, changeUserEmail: JsonChangeUserEmail): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(ChangeUserEmail.`type`)))
      case _ => None
    }
  }
}
