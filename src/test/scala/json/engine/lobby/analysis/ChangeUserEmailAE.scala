package json.engine.lobby.analysis

import json.engine.lobby.LobbyBox
import json.engine.lobby.example.ChangeUserEmail
import json.element.JsonTest
import licos.json.element.lobby.client2server.JsonChangeUserEmail
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.ChangeUserEmailAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class ChangeUserEmailAE extends ChangeUserEmailAnalysisEngine {
  override def process(box: BOX, changeUserEmail: JsonChangeUserEmail): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(ChangeUserEmail.`type`)))
      case _ => Left(Json.toJson(changeUserEmail))
    }
  }
}
