package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.Play
import element.JsonTest
import licos.json.element.lobby.JsonPlay
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.PlayAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class PlayAE extends PlayAnalysisEngine {
  override def process(box: BOX, play: JsonPlay): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(Play.`type`)))
      case _ => None
    }
  }
}
