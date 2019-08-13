package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.AvatarInfo
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.AvatarInfoAnalysisEngine
import licos.json.lobby.JsonAvatarInfo
import play.api.libs.json.{JsValue, Json}

class AvatarInfoAE extends AvatarInfoAnalysisEngine {

  override def process(box: BOX, avatar: JsonAvatarInfo): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(AvatarInfo.`type`)))
      case _ => None
    }
  }

}