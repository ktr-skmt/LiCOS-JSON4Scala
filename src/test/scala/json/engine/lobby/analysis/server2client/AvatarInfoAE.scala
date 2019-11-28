package json.engine.lobby.analysis.server2client

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.server2client.AvatarInfo
import licos.json.element.lobby.server2client.JsonAvatarInfo
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
