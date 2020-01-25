package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.ChangeAvatar
import licos.json.element.lobby.client2server.JsonChangeAvatar
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.ChangeAvatarAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class ChangeAvatarAE extends ChangeAvatarAnalysisEngine {
  override def process(box: BOX, updateAvatar: JsonChangeAvatar): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(ChangeAvatar.`type`)))
      case _ => Left(Json.toJson(updateAvatar))
    }
  }
}
