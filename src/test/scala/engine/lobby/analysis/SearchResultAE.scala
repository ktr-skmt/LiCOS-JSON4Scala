package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.SearchResult
import element.JsonTest
import licos.json.element.lobby.JsonSearchResult
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.SearchResultAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class SearchResultAE extends SearchResultAnalysisEngine {
  override def process(box: BOX, searchResult: JsonSearchResult): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(SearchResult.`type`)))
      case _ => None
    }
  }
}
