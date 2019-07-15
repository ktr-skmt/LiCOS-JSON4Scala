package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.ChangeUserName
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.ChangeUserNameAnalysisEngine
import licos.json.lobby.JsonChangeUserName
import play.api.libs.json.{JsValue, Json}

class ChangeUserNameAE extends ChangeUserNameAnalysisEngine {
  override def process(box: BOX, changeUserName: JsonChangeUserName): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(ChangeUserName.`type`)))
      case _ => None
    }
  }
}
