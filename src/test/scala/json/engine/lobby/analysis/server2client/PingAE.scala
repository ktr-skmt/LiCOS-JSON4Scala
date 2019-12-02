package json.engine.lobby.analysis.server2client

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.server2client.Ping
import licos.json.element.lobby.server2client.JsonPing
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.PingAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class PingAE extends PingAnalysisEngine {
  override def process(box: BOX, ping: JsonPing): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(Ping.`type`)))
      case _ => Left(Json.toJson(ping))
    }
  }
}
