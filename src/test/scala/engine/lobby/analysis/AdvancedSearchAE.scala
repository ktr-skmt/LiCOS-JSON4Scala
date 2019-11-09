package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.AdvancedSearch
import element.JsonTest
import licos.json.element.lobby.JsonAdvancedSearch
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.AdvancedSearchAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class AdvancedSearchAE extends AdvancedSearchAnalysisEngine {
  override def process(box: BOX, advancedSearch: JsonAdvancedSearch): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(AdvancedSearch.`type`)))
      case _ => Left(Json.toJson(advancedSearch))
    }
  }
}
