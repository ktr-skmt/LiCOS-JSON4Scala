package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.IdSearch
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.IdSearchAnalysisEngine
import licos.json.lobby.JsonIdSearch
import play.api.libs.json.{JsValue, Json}

class IdSearchAE extends IdSearchAnalysisEngine {
  override def process(box: BOX, idSearch: JsonIdSearch): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(IdSearch.`type`)))
      case _ => None
    }
  }
}
