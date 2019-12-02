package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.Play
import licos.json.element.lobby.client2server.JsonPlay
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.PlayAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class PlayAE extends PlayAnalysisEngine {
  override def process(box: BOX, play: JsonPlay): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(Play.`type`)))
      case _ => Left(Json.toJson(play))
    }
  }
}
