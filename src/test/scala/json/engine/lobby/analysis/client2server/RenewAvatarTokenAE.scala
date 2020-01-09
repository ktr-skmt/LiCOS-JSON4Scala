package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.RenewAvatarToken
import licos.json.element.lobby.client2server.JsonRenewAvatarToken
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.RenewAvatarTokenAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class RenewAvatarTokenAE extends RenewAvatarTokenAnalysisEngine {

  override def process(box: BOX, renewAvatarToken: JsonRenewAvatarToken): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(RenewAvatarToken.`type`)))
      case _ => Left(Json.toJson(renewAvatarToken))
    }
  }

}
