package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.AdvancedSearch
import licos.json.element.lobby.client2server.JsonAdvancedSearch
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
