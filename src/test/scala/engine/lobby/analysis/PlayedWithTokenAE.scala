package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.PlayedWithToken
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.PlayedWithTokenAnalysisEngine
import licos.json.lobby.JsonPlayedWithToken
import play.api.libs.json.{JsValue, Json}

class PlayedWithTokenAE extends PlayedWithTokenAnalysisEngine {
  override def process(box: BOX, playedWithToken: JsonPlayedWithToken): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(PlayedWithToken.`type`)))
      case _ => None
    }
  }
}
