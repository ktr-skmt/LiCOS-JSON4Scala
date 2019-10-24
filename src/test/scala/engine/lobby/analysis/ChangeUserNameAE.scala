package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.ChangeUserName
import element.JsonTest
import licos.json.element.lobby.JsonChangeUserName
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.ChangeUserNameAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class ChangeUserNameAE extends ChangeUserNameAnalysisEngine {
  override def process(box: BOX, changeUserName: JsonChangeUserName): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(ChangeUserName.`type`)))
      case _ => None
    }
  }
}