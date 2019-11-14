package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.AvatarInfo
import element.JsonTest
import licos.json.element.lobby.JsonAvatarInfo
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.AvatarInfoAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class AvatarInfoAE extends AvatarInfoAnalysisEngine {

  override def process(box: BOX, avatar: JsonAvatarInfo): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(AvatarInfo.`type`)))
      case _ => Left(Json.toJson(avatar))
    }
  }

}
