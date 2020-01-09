package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.DeleteAvatar
import licos.json.element.lobby.client2server.JsonDeleteAvatar
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.DeleteAvatarAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class DeleteAvatarAE extends DeleteAvatarAnalysisEngine {
  override def process(box: BOX, deleteAvatar: JsonDeleteAvatar): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(DeleteAvatar.`type`)))
      case _ => Left(Json.toJson(deleteAvatar))
    }
  }
}
