package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonAdvancedSearch
import play.api.libs.json.JsValue

/** The analysis engine for advanced search.
  *
  * @author Kotaro Sakamoto
  */
trait AdvancedSearchAnalysisEngine extends AnalysisEngine {

  /**
    *
    * @param box
    * @param advancedSearch
    * @return
    */
  def process(box: BOX, advancedSearch: JsonAdvancedSearch): Option[JsValue]
}
