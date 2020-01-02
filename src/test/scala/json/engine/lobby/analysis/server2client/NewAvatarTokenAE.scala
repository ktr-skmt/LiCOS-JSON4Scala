package json.engine.lobby.analysis.server2client

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.server2client.NewAvatarToken
import licos.json.element.lobby.server2client.JsonNewAvatarToken
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.NewAvatarTokenAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class NewAvatarTokenAE extends NewAvatarTokenAnalysisEngine {

  override def process(box: BOX, newAvatarToken: JsonNewAvatarToken): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(NewAvatarToken.`type`)))
      case _ => Left(Json.toJson(newAvatarToken))
    }
  }

}
