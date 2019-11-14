package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.Pong
import element.JsonTest
import licos.json.element.lobby.JsonPong
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.PongAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class PongAE extends PongAnalysisEngine {
  override def process(box: BOX, pong: JsonPong): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(Pong.`type`)))
      case _ => Left(Json.toJson(pong))
    }
  }
}
