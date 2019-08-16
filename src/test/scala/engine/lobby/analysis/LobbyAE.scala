package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.Lobby
import element.JsonTest
import licos.json.element.lobby.JsonLobby
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.LobbyAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class LobbyAE extends LobbyAnalysisEngine {
  override def process(box: BOX, lobby: JsonLobby): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(Lobby.`type`)))
      case _ => None
    }
  }
}
