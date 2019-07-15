package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.SearchResult
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.SearchResultAnalysisEngine
import licos.json.lobby.JsonSearchResult
import play.api.libs.json.{JsValue, Json}

class SearchResultAE extends SearchResultAnalysisEngine {
  override def process(box: BOX, searchResult: JsonSearchResult): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(SearchResult.`type`)))
      case _ => None
    }
  }
}
