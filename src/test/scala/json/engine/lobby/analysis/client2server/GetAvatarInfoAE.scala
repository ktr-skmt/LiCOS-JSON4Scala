package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.GetAvatarInfo
import licos.json.element.lobby.server2client.JsonGetAvatarInfo
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.GetAvatarInfoAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class GetAvatarInfoAE extends GetAvatarInfoAnalysisEngine {
  override def process(box: BOX, getAvatarInfo: JsonGetAvatarInfo): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(GetAvatarInfo.`type`)))
      case _ => Left(Json.toJson(getAvatarInfo))
    }
  }
}
