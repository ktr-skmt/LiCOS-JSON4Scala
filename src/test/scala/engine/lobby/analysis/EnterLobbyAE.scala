package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.EnterLobby
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.EnterLobbyAnalysisEngine
import licos.json.lobby.JsonEnterLobby
import play.api.libs.json.{JsValue, Json}

class EnterLobbyAE extends EnterLobbyAnalysisEngine {
  override def process(box: BOX, enterLobby: JsonEnterLobby): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(EnterLobby.`type`)))
      case _ => None
    }
  }
}
