package json.engine.lobby.analysis.server2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.server2server.PlayedWithToken
import licos.json.element.lobby.server2server.JsonPlayedWithToken
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2server.PlayedWithTokenAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class PlayedWithTokenAE extends PlayedWithTokenAnalysisEngine {
  override def process(box: BOX, playedWithToken: JsonPlayedWithToken): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(PlayedWithToken.`type`)))
      case _ => Left(Json.toJson(playedWithToken))
    }
  }
}
