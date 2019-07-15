package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonSearchResult
import play.api.libs.json.JsValue

trait SearchResultAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, searchResult: JsonSearchResult): Option[JsValue]
}
