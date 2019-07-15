package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.Pong
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.PongAnalysisEngine
import licos.json.lobby.JsonPong
import play.api.libs.json.{JsValue, Json}

class PongAE extends PongAnalysisEngine {
  override def process(box: BOX, pong: JsonPong): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(Pong.`type`)))
      case _ => None
    }
  }
}
