package licos.json.engine.analysis.lobby.server2client

import licos.json.element.lobby.server2client.JsonSearchResult
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for a search result.
  *
  * @author Kotaro Sakamoto
  */
trait SearchResultAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param searchResult a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, searchResult: JsonSearchResult): Either[JsValue, JsValue]

}

object SearchResultAnalysisEngine {

  /**
    * Search-result analysis engine name.
    */
  val name:         String  = "lobby.server2client.SearchResultAnalysisEngine"
  val isFromServer: Boolean = true

}
