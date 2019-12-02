package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.ChangeUserPassword
import licos.json.element.lobby.client2server.JsonChangeUserPassword
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.ChangeUserPasswordAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class ChangeUserPasswordAE extends ChangeUserPasswordAnalysisEngine {
  override def process(box: BOX, changeUserPassword: JsonChangeUserPassword): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(ChangeUserPassword.`type`)))
      case _ => Left(Json.toJson(changeUserPassword))
    }
  }
}
