package json.engine.lobby.analysis

import json.engine.lobby.LobbyBox
import json.engine.lobby.example.Pong
import json.element.JsonTest
import licos.json.element.lobby.client2server.JsonPong
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
