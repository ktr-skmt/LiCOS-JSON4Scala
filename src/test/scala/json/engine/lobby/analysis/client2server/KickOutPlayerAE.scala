package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.KickOutPlayer
import licos.json.element.lobby.client2server.JsonKickOutPlayer
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.KickOutPlayerAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class KickOutPlayerAE extends KickOutPlayerAnalysisEngine {
  override def process(box: BOX, kickOutPlayer: JsonKickOutPlayer): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(KickOutPlayer.`type`)))
      case _ => Left(Json.toJson(kickOutPlayer))
    }
  }
}
