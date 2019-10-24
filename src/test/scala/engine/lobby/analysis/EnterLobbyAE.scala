package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.EnterLobby
import element.JsonTest
import licos.json.element.lobby.JsonEnterLobby
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.EnterLobbyAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class EnterLobbyAE extends EnterLobbyAnalysisEngine {
  override def process(box: BOX, enterLobby: JsonEnterLobby): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(EnterLobby.`type`)))
      case _ => None
    }
  }
}