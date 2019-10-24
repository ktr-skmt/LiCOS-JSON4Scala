package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.ChangeUserPassword
import element.JsonTest
import licos.json.element.lobby.JsonChangeUserPassword
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.ChangeUserPasswordAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class ChangeUserPasswordAE extends ChangeUserPasswordAnalysisEngine {
  override def process(box: BOX, changeUserPassword: JsonChangeUserPassword): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(ChangeUserPassword.`type`)))
      case _ => None
    }
  }
}