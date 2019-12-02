package json.engine.lobby.analysis.server2client

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.server2client.Lobby
import licos.json.element.lobby.server2client.JsonLobby
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.LobbyAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class LobbyAE extends LobbyAnalysisEngine {
  override def process(box: BOX, lobby: JsonLobby): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(Lobby.`type`)))
      case _ => Left(Json.toJson(lobby))
    }
  }
}
