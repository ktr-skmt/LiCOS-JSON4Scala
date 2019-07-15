package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.Play
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.PlayAnalysisEngine
import licos.json.lobby.JsonPlay
import play.api.libs.json.{JsValue, Json}

class PlayAE extends PlayAnalysisEngine {
  override def process(box: BOX, play: JsonPlay): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(Play.`type`)))
      case _ => None
    }
  }
}
