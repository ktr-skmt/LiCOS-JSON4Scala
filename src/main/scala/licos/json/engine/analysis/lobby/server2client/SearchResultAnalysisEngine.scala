package licos.json.engine.analysis.lobby.server2client

import licos.json.element.lobby.JsonSearchResult
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
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, searchResult: JsonSearchResult): Option[JsValue]
}
