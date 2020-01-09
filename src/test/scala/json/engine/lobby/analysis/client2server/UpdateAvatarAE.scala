package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.UpdateAvatar
import licos.json.element.lobby.client2server.JsonUpdateAvatar
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.UpdateAvatarAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class UpdateAvatarAE extends UpdateAvatarAnalysisEngine {
  override def process(box: BOX, updateAvatar: JsonUpdateAvatar): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(UpdateAvatar.`type`)))
      case _ => Left(Json.toJson(updateAvatar))
    }
  }
}
