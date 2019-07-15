package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.ChangeUserEmail
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.ChangeUserEmailAnalysisEngine
import licos.json.lobby.JsonChangeUserEmail
import play.api.libs.json.{JsValue, Json}

class ChangeUserEmailAE extends ChangeUserEmailAnalysisEngine {
  override def process(box: BOX, changeUserEmail: JsonChangeUserEmail): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(ChangeUserEmail.`type`)))
      case _ => None
    }
  }
}
