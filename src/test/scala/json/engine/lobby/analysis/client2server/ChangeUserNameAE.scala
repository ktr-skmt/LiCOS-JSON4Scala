package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.ChangeUserName
import licos.json.element.lobby.client2server.JsonChangeUserName
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.ChangeUserNameAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class ChangeUserNameAE extends ChangeUserNameAnalysisEngine {
  override def process(box: BOX, changeUserName: JsonChangeUserName): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(ChangeUserName.`type`)))
      case _ => Left(Json.toJson(changeUserName))
    }
  }
}
