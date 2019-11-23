package json.engine.lobby.analysis

import json.engine.lobby.LobbyBox
import json.engine.lobby.example.Played
import json.element.JsonTest
import licos.json.element.lobby.JsonPlayed
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.PlayedAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class PlayedAE extends PlayedAnalysisEngine {
  override def process(box: BOX, played: JsonPlayed): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(Played.`type`)))
      case _ => Left(Json.toJson(played))
    }
  }
}
