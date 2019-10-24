package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.GetAvatarInfo
import element.JsonTest
import licos.json.element.lobby.JsonGetAvatarInfo
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.GetAvatarInfoAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class GetAvatarInfoAE extends GetAvatarInfoAnalysisEngine {
  override def process(box: BOX, getAvatarInfo: JsonGetAvatarInfo): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(GetAvatarInfo.`type`)))
      case _ => None
    }
  }
}