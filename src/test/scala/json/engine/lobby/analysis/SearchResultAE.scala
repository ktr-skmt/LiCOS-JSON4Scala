package json.engine.lobby.analysis

import json.engine.lobby.LobbyBox
import json.engine.lobby.example.SearchResult
import json.element.JsonTest
import licos.json.element.lobby.JsonSearchResult
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.SearchResultAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class SearchResultAE extends SearchResultAnalysisEngine {
  override def process(box: BOX, searchResult: JsonSearchResult): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(SearchResult.`type`)))
      case _ => Left(Json.toJson(searchResult))
    }
  }
}
