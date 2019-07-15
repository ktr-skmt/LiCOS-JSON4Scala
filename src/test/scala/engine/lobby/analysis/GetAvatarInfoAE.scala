package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.GetAvatarInfo
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.GetAvatarInfoAnalysisEngine
import licos.json.lobby.JsonGetAvatarInfo
import play.api.libs.json.{JsValue, Json}

class GetAvatarInfoAE extends GetAvatarInfoAnalysisEngine {
  override def process(box: BOX, getAvatarInfo: JsonGetAvatarInfo): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(GetAvatarInfo.`type`)))
      case _ => None
    }
  }
}
