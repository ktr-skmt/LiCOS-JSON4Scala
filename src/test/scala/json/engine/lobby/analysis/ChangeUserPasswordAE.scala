package json.engine.lobby.analysis

import json.engine.lobby.LobbyBox
import json.engine.lobby.example.ChangeUserPassword
import json.element.JsonTest
import licos.json.element.lobby.JsonChangeUserPassword
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.ChangeUserPasswordAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class ChangeUserPasswordAE extends ChangeUserPasswordAnalysisEngine {
  override def process(box: BOX, changeUserPassword: JsonChangeUserPassword): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(ChangeUserPassword.`type`)))
      case _ => Left(Json.toJson(changeUserPassword))
    }
  }
}
